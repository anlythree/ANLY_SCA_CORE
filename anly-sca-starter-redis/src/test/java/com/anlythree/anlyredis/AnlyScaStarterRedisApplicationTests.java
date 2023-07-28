package com.anlythree.anlyredis;

import com.anlythree.anlyredis.core.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnlyScaStarterRedisApplicationTests {

	@Autowired
	private RedisService redisService;

	@Test
	void testConnect() {
		String aValue = redisService.get("a").toString();
		System.out.println(aValue);
	}

}
