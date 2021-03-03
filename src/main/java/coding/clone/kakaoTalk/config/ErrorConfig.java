package coding.clone.kakaoTalk.config;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.validation.Errors;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class ErrorConfig extends JsonSerializer<Errors> {

	@Override
	public void serialize(Errors errors, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartArray();
		
		errors.getFieldErrors().stream().forEach(e -> {
			try {
				gen.writeStartObject();
				
				gen.writeStringField("field", e.getField());
				gen.writeStringField("objectName", e.getObjectName());
				gen.writeStringField("code", e.getCode());
				gen.writeStringField("defaultMessage", e.getDefaultMessage());
				
				Object rejectedValue = e.getRejectedValue();
				
				if (rejectedValue != null) {
					gen.writeObjectField("rejectedValue", rejectedValue);
				}
				
				gen.writeEndObject();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		});
		
		errors.getGlobalErrors().forEach(e -> {
			try {
				gen.writeStartObject();
				
				gen.writeStringField("objectName", e.getObjectName());
				gen.writeStringField("code", e.getCode());
				gen.writeStringField("defaultMessage", e.getDefaultMessage());
				
				gen.writeEndObject();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		});
	}
}
