package coding.clone.kakaoTalk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MapperUtil extends ModelMapper {	
	/**
	 * List Class Converter
	 * List Class 변환
	 * 
	 * @param <S>
	 * @param <T>
	 * @param source
	 * @param targetClass
	 * @return
	 */
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
		ModelMapper modelMapper = new ModelMapper();
		
		try {
			return source
					.stream()
					.map(element -> modelMapper.map(element, targetClass))
					.collect(Collectors.toList());
		} catch(Exception e) {
			new Exception(e);
			
			return null;
		}
	}
	
	/**
	 * String to List<HashMap>
	 * 문자열을 List<HashMap>으로 변환
	 * 
	 * @param jsonStr
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static List<HashMap<String, Object>> toList(String jsonStr) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			List<HashMap<String, Object>> list = new ArrayList<>();
			list = objectMapper.readValue(jsonStr, new TypeReference<List<HashMap<String, Object>>>() {});
			
			return list;
		} catch(Exception e) {
			new Exception(e);
			
			return null;
		}
	}
	
	/**
	 * String to HashMap
	 * 문자열을 HashMap으로 변환
	 * 
	 * @param jsonStr
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static HashMap<String, Object> toHashMap(String jsonStr) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			HashMap<String, Object> map = new HashMap<>();
			map = objectMapper.readValue(jsonStr, new TypeReference<HashMap<String, Object>>(){});
			
			return map;
		} catch(Exception e) {
			new Exception(e);
			
			return null;
		}
	}
	
	/**
	 * HashMap to Sting
	 * HashMap을 문자열로 변환
	 * 
	 * @param map
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String toJson(HashMap<String, Object> map) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String json = "";
			json = objectMapper.writer().writeValueAsString(map);
			
			return json;
		} catch(Exception e) {
			new Exception(e);
			
			return null;
		}
	}
	
	/**
	 * Json(String) to Object class
	 * 문자열을 Class 객체로 변환
	 * 
	 * @param <S>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static <S> S toClass(String jsonStr, Class<S> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			S cls = objectMapper.readValue(jsonStr, clazz);
			
			return cls;
		} catch (Exception e) {
			new Exception(e);
			
			return null;
		}
	}
	
	/**
	 * Json(String) to List<Object Class>
	 * 문자열을 List<Class> 객체로 변환
	 * 
	 * @param <S>
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	public static <S> List<S> toClassList(String jsonStr, Class<S> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			List<S> classList = objectMapper.readValue(jsonStr, new TypeReference<List<S>>() {});
	
			return classList;
		} catch(Exception e) {
			new Exception(e);
			
			return null;
		}
	}
}
