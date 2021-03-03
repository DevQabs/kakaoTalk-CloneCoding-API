package coding.clone.kakaoTalk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coding.clone.kakaoTalk.service.PollingService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/poll", produces = MediaTypes.HAL_JSON_VALUE)
public class PollingController {

	@Autowired
	private PollingService pollService;
	
	@GetMapping("/{roomId}")
	public ResponseEntity<?> subscribeMessage(
			String roomId
			) {
		ResponseEntity<?> response = pollService.subscribeMessage(roomId);
		
		return response;
	}
	
}
