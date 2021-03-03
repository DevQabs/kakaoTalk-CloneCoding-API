package coding.clone.kakaoTalk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import coding.clone.kakaoTalk.dto.MessageDto;
import coding.clone.kakaoTalk.entity.Message;
import coding.clone.kakaoTalk.kafka.producer.Producer;
import coding.clone.kakaoTalk.resource.MessageResource;
import coding.clone.kakaoTalk.util.MapperUtil;

@Service
public class MessageService {

	@Autowired
	private Producer producer;
	
	public ResponseEntity<?> publishMessage(List<MessageDto> messageDtoList) {				
		String topic = "SEND_MESSAGE";
		
		List<Message> messageList = MapperUtil.mapList(messageDtoList, Message.class);
		
		boolean isSend = producer.sendSyncMessage(topic, messageList);
		
		if (!isSend) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		List<MessageDto> sendMessageDtoList = MapperUtil.mapList(messageList, MessageDto.class);
		
		MessageResource messageResource = new MessageResource(sendMessageDtoList);
		
		return ResponseEntity.ok(messageResource);
	}
	
}
