package coding.clone.kakaoTalk.validator;

import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import coding.clone.kakaoTalk.dto.MessageDto;
import coding.clone.kakaoTalk.entity.Message;

@Component
public class MessageValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof Collection<?>) {
			((Collection<?>) target).forEach(t -> {
				this.validate(t, errors);
			});
		}
		else if (target instanceof MessageDto) {
			dtoValidate((MessageDto) target, errors);
		}
		else if (target instanceof Message) {
			entityValidate((Message) target, errors);
		}
		else {
			errors.reject("Object Class is Not Matching.");
		}
	}
	
	private void dtoValidate(MessageDto user, Errors errors) {
		
	}

	private void entityValidate(Message user, Errors errors) {
		
	}
}
