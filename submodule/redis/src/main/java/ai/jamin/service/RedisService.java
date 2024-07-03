package ai.jamin.service;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
	private static final String HASH_NAME = "postViews";
	private final HashOperations<String, Long, Integer> hashOperations;

	public void incrementCount(Long articleId) {
		hashOperations.increment(HASH_NAME, articleId, 1);
	}

	public Integer getCounterValue(Long articleId) {
		Integer value = hashOperations.get(HASH_NAME, articleId);
		return value != null ? value : 0;
	}

	public Map<Long, Integer> getAllCounters() {
		return hashOperations.entries(HASH_NAME);
	}

	public void deleteCounter(Long articleId) {
		hashOperations.delete(HASH_NAME, articleId);
	}
}
