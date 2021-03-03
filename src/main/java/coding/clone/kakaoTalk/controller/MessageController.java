package coding.clone.kakaoTalk.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coding.clone.kakaoTalk.dto.MessageDto;
import coding.clone.kakaoTalk.service.MessageService;
import coding.clone.kakaoTalk.validator.MessageValidator;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/message", produces = MediaTypes.HAL_JSON_VALUE)
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageValidator messageValidator;
	
	@PostMapping("/send")
	public ResponseEntity<?> publishMessage(
			@RequestBody List<@Valid MessageDto> messageList,
			BindingResult bindingResult
			) {
		messageValidator.validate(messageList, bindingResult);
		
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		
		ResponseEntity<?> responseEntity = messageService.publishMessage(messageList);
		
		return responseEntity;
	}
	
}
