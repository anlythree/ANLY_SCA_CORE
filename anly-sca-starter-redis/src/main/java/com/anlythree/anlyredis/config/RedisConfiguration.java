package com.anlythree.anlyredis.config;

import com.anlythree.anlyredis.core.RedisService;
import com.anlythree.anlyredis.props.AnlyRedisProperties;
import com.anlythree.anlyredis.util.RedisLockUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis基础配置类
 *
 * @author anlythree
 */
@Configuration
@EnableConfigurationProperties(AnlyRedisProperties.class)
@ConditionalOnProperty(value = AnlyRedisProperties.PREFIX + ".enabled", havingValue = "true", matchIfMissing = true)
public class RedisConfiguration {

	@Bean
	public RedisSerializer<String> redisKeySerializer() {
		return RedisSerializer.string();
	}

	@Bean
	public RedisSerializer<Object> redisValueSerializer() {
		return RedisSerializer.json();
	}

	@SuppressWarnings("all")
	@Bean(name = "redisTemplate")
	@ConditionalOnClass(RedisOperations.class)
	public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(factory);

		Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisService redisService() {
		return new RedisService();
	}

	@Bean
	@ConditionalOnBean(name = "redisTemplate")
	public RedisLockUtil redisLockUtil(RedisTemplate redisTemplate) {
		return new RedisLockUtil(redisTemplate);
	}
}
