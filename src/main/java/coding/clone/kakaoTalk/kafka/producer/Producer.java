package coding.clone.kakaoTalk.kafka.producer;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import coding.clone.kakaoTalk.entity.Message;

@Component
public class Producer {
	
	/**
	 * 동기 카프카 전송
	 * 
	 * @param transactionId
	 * @param topic
	 * @param notiMessageList
	 */
	public boolean sendSyncMessage(String topic, List<Message> messageList) {
		Properties props = producerProperties();
		props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, UUID.randomUUID().toString());
		
		KafkaProducer<String, Message> producer = new KafkaProducer<>(props);
		
		producer.initTransactions();
		
		try {
			producer.beginTransaction();
			
			messageList.forEach(msg -> {
				ProducerRecord<String, Message> record = new ProducerRecord<>(topic, msg.getId().toString(), msg);
				producer.send(record);
			});
			
			producer.commitTransaction();
			producer.close();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			producer.close();
			
			return false;
		}
	}
	
	/**
	 * 비동기 카프카 전송
	 * 
	 * @param topic
	 * @param notiMessageList
	 */
	public void sendAsyncMessage(String topic, List<Message> messageList) {
		Properties props = producerProperties();
		
		KafkaProducer<String, Message> producer = new KafkaProducer<>(props);
		messageList.forEach(msg -> {
			ProducerRecord<String, Message> record = new ProducerRecord<>(topic, msg.getId().toString(), msg);
			producer.send(record, callback());
		});
		
		producer.close();
	}
	
	/**
	 * 속성 설정 헬퍼 메서드
	 * 
	 * @return Properties
	 */
	private Properties producerProperties() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ProducerConfig.ACKS_CONFIG, "all");
		props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return props;
	}
	
	/**
	 * 비동기 카프카 전송 콜백 메서드
	 * 
	 * @return Callback
	 */
	private Callback callback() {
		Callback callback = new Callback() {
			
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception == null) {
					// success
				} else {
					// fail (error)
				}
			}
		};
		
		return callback;
	}
}
