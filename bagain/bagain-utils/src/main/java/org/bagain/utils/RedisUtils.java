package org.bagain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;



public class RedisUtils {

	private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);
	
	private static StringRedisTemplate jedisTemplate = SpringBeansUtils.getBean(StringRedisTemplate.class);
	
	public static String get(final String key) {
	      String v = null;
	      try {
	    	  ValueOperations<String,String> vo = jedisTemplate.opsForValue();
	    	  v = vo.get(key);
	      } catch (Exception e) {
	          logger.error("redis get失败，key:" + key);
	      }
	      return v;
	}
	
	public static void set(final String k,String v) {
      try {
    	  ValueOperations<String,String> vo = jedisTemplate.opsForValue();
    	  vo.set(k, v);
      } catch (Exception e) {
          logger.error("redis set失败，key:" + k);
      }
	}
}
