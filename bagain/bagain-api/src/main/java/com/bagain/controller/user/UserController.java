package com.bagain.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.bagain.utils.JSONMessage;
import org.bagain.utils.RedisUtils;
import org.bagain.utils.ReqUtil;
import org.bagain.utils.SpringBeansUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bagain.mongodb.dao.CommonInterfaceDao;
import com.bagain.mongodb.dao.InterfaceDao;
import com.bagain.mongodb.vo.VoTest;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Value("${application.hell:Hello Shanhy}")
	String abc ;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	CommonInterfaceDao commonInterfaceDao;
	
	@Autowired
	InterfaceDao interfaceDao;
	
	@GetMapping(value = "test",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONMessage test() {
		ValueOperations<String,String> vo = stringRedisTemplate.opsForValue();
		vo.set("abc", "abc");
		String abc = vo.get("abc");
		System.out.println(abc);
		stringRedisTemplate.expire(abc, 1, TimeUnit.SECONDS);
		VoTest po = commonInterfaceDao.findById("200108220021");
		Page<VoTest> pageVo = commonInterfaceDao.findByNameRegex("^北",0,20);
		VoTest po1 = interfaceDao.findOne("200108220021");
		Page<VoTest> pageVo1 = interfaceDao.findByNameRegex("^北", new PageRequest(0, 20));
		Map<String,Object> map = new HashMap<>();
		map.put("po", po);
		map.put("pageVo", pageVo);
		map.put("po1", po1);
		map.put("pageVo1", pageVo1);
		map.put("abc", abc);
		StringRedisTemplate aaa = SpringBeansUtils.getBean(StringRedisTemplate.class);
		System.out.println(aaa);
		return JSONMessage.success(map);
    }
	
	@GetMapping(value = "login",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONMessage login() {
		Map<String,Object> map = new HashMap<>();
		int userId = 199;
		String token = UUID.randomUUID().toString().replace("-", "");
		RedisUtils.set(ReqUtil.userTokenK(token), userId+"");
		map.put("userId", userId);
		map.put("token", token);
		return JSONMessage.success(map);
    }
	
	@GetMapping(value = "testError",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONMessage testError() {
		int t = 2/0;
		return JSONMessage.success();
    }

}



