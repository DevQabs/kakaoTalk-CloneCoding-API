package coding.clone.kakaoTalk.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
	
	private String id;
	
	private String message;
	
	private String roomId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime sendMessageTimestamp;
}
