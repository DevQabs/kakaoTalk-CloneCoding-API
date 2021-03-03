package coding.clone.kakaoTalk.resource;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import coding.clone.kakaoTalk.controller.MessageController;
import coding.clone.kakaoTalk.dto.MessageDto;
import coding.clone.kakaoTalk.util.LinkUtil;

public class MessageResource extends EntityModel<MessageDto> {

	@JsonUnwrapped
	private MessageDto messageDto;
	
	@JsonUnwrapped
	private CollectionModel<EntityModel<MessageDto>> messageDtoList;
	
	private Link defaultSelfRel() {
		WebMvcLinkBuilder messageLinkTo = linkTo(MessageController.class);
		
		return messageLinkTo.withSelfRel();
	}
	
	public MessageResource() {
		add(defaultSelfRel());
	}
	
	public MessageResource(List<MessageDto> messageDtoList, Link ...links) {
		this.messageDtoList = LinkUtil.ListToCollectModel(messageDtoList, MessageController.class, MessageDto::getId);
	}
}
