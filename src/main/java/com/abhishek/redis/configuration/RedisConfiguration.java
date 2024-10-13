package com.abhishek.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
	
	
	@Bean
	public LettuceConnectionFactory getConnectionFactory()
	{
		return new LettuceConnectionFactory();
	}
	
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		
		// Setting the connection factory
		redisTemplate.setConnectionFactory(getConnectionFactory());
		
		
		// We need to set the key serializer that can convert to and fro between redis and Spring
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		
		// Same as Key, we need to set the serializer for value as well
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		return redisTemplate;
	}
	
	

}
