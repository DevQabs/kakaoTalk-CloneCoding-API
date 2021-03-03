package coding.clone.kakaoTalk.kafka.consumer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import coding.clone.kakaoTalk.entity.Message;
import coding.clone.kakaoTalk.kafka.producer.Producer;
import coding.clone.kakaoTalk.repository.MessageRepository;

@Service
public class Consumer {

	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private Producer producer;
	
	@KafkaListener(topics = "SEND_MESSAGE")
	public void countInsertConsume(@Payload Message message) throws Exception {
		Message newMessage = messageRepo.save(message);

		List<Message> messageList = new ArrayList<>();
		messageList.add(newMessage);
		
		String topic = message.getRoomId();
		producer.sendSyncMessage(topic, messageList);
	}
	
}
