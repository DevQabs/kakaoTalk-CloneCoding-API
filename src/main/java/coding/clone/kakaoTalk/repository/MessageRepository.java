package coding.clone.kakaoTalk.repository;

import org.springframework.data.repository.CrudRepository;

import coding.clone.kakaoTalk.entity.Message;

public interface MessageRepository extends CrudRepository<Message, String> {


}
