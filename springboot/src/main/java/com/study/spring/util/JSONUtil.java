package com.study.spring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON util
 *
 * @author Jeffrey
 * @since 2017/2/21 14:31
 */
public class JSONUtil {

	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();

	static {
		//默认日期格式
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	private JSONUtil() {
	}

	public static ObjectMapper getInstance() {
		return objectMapper;
	}

	/**
	 * 对象转json字符串(对象必须是一个javaBean)
	 *
	 * @return json字符串
	 */
	public static String obj2json(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("对象转json字符串失败,message:[" + e.getMessage() + "]");
		}
		return null;
	}

	/**
	 * json字符串转对象
	 *
	 * @param jsonStr json字符串
	 * @param clazz 对象class类型
	 * @return 对象
	 */
	public static <T> T json2obj(String jsonStr, Class<T> clazz) {
		try {
			return objectMapper.readValue(jsonStr, clazz);
		} catch (Exception e) {
			logger.error("json字符串转对象失败,message:[" + e.getMessage() + "]");
		}
		return null;
	}

	/**
	 * json字符串转map
	 *
	 * @return map
	 */
	public static <T> Map<String, Object> json2map(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			logger.error("json转map失败,message:[" + e.getMessage() + "]");
		}
		return null;
	}

	/**
	 * map转json字符串
	 */
	public static String map2json(Map<String, Object> map) {
		try {
			return objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			logger.error("map转json字符串失败,message:[" + e.getMessage() + "]");
		}
		return null;
	}

	/**
	 * json字符串转map
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) {
		Map<String, Map<String, Object>> map = null;
		try {
			map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
			});
		} catch (Exception e) {
			logger.error("json字符串转map失败,message:[" + e.getMessage() + "]");
		}
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json字符串转对象集合
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) {
		List<Map<String, Object>> list = null;
		try {
			list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
			});
		} catch (Exception e) {
			logger.error("json字符串转对象集合,message:[" + e.getMessage() + "]");
		}
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map转对象
	 *
	 * @return 对象
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
}
