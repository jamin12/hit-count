package ai.jamin.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisServiceTest {
	@Autowired
	private RedisService redisService;

	@Autowired
	private HashOperations<String, Long, Integer> hashOperations;
	@Autowired
	private RedisTemplate<String, Object> objectRedisTemplate;
	private static final String HASH_NAME = "postViews";

	@BeforeEach
	void setUp() {
		objectRedisTemplate.delete(HASH_NAME);
	}

	@Test
	void incrementCount() {
		// given
		redisService.incrementCount(1L);

		// when
		Integer counterValue = hashOperations.get(HASH_NAME, "1");

		// then
		assertEquals(1, counterValue.intValue());
	}

	@Test
	void getCounterValue() {
		// given
		hashOperations.increment(HASH_NAME, 1L, 1);

		// when
		Integer counterValue = redisService.getCounterValue(1L);

		// then
		assertEquals(1, counterValue.intValue());
	}

	@Test
	void getAllCounters() {
		// given
		hashOperations.increment(HASH_NAME, 1L, 1);
		hashOperations.increment(HASH_NAME, 2L, 1);

		// when
		Map<Long, Integer> allCounters = redisService.getAllCounters();

		// then
		assertEquals(2, allCounters.size());
		assertEquals(1, allCounters.get(1L));
	}
}