package coding.clone.kakaoTalk.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

@Component
public class LinkUtil {
	/**
	 * List 를 CollectionModel로 변환
	 * 
	 * @param <S> Controller Class
	 * @param <T> 대상 Class
	 * @param list 대상 리스트
	 * @param clazz Controller Class
	 * @param func 해당 키값 getter Functional Interface
	 * @return CollectionModel<EntityModel<대상Class>>
	 */
	public static <S, T> CollectionModel<EntityModel<T>> ListToCollectModel(
			List<T> list,
			Class<S> clazz,
			Function<T, ?> func
			) {
		
		List<EntityModel<T>> entityList = 
				list
					.stream()
					.map(entity ->
							EntityModel
								.of(entity)
								.add(linkTo(clazz.getClass()).slash(func.apply(entity)).withSelfRel())
						)
					.collect(Collectors.toList());
		
		return CollectionModel.of(entityList);
	}
}
