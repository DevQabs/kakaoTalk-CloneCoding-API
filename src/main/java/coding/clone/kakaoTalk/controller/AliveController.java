package coding.clone.kakaoTalk.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/aliveCheck", produces = MediaTypes.HAL_JSON_VALUE)
@CrossOrigin(origins = "*", maxAge = 3600)
public class AliveController {
	
	@GetMapping
	public ResponseEntity<?> getAliveCheck() throws Exception {
		ResponseEntity<?> responseEntity = ResponseEntity.ok().build();
		
		return responseEntity;
	}
}
