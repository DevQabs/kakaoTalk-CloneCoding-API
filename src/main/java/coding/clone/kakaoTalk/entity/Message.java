package coding.clone.kakaoTalk.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(indexName = "kakaoTalk")
public class Message {

	@Id
	@GeneratedValue(generator = "id")
	@GenericGenerator(name = "id", strategy = "coding.clone.kakaoTalk.generator.MessageKeyGenerator")
	private String id;
	
	private String message;
	
	private String roomId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime sendMessageTimestamp;
	
}
