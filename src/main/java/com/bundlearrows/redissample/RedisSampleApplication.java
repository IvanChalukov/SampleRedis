package com.bundlearrows.redissample;

import com.bundlearrows.redissample.service.RedisService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisSampleApplication implements CommandLineRunner {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;


	public static void main(String[] args) {
		SpringApplication.run(RedisSampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Perform Redis operations here
		redisTemplate.opsForValue().set("key", "value");
		String value = redisTemplate.opsForValue().get("key");
		System.out.println("Retrieved value from Redis: " + value);
	}
}
