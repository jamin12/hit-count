package ai.jamin.domain.post.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.jamin.post.service.PostDailyHitsService;
import ai.jamin.post.service.PostService;
import ai.jamin.service.RedisService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PostScheduleService {
	private final PostDailyHitsService postDailyHitsService;
	private final PostService postService;
	private final RedisService redisService;

	@Scheduled(fixedDelay = 10000)
	public void updateHits() {
		Map<Long, Integer> allCounters = redisService.getAllCounters();

		allCounters.forEach((postId, hitCount) -> {
			postService.getOptionalById(postId).ifPresent(value -> {
					value.incrementHitCount(hitCount.longValue());
					postDailyHitsService.incrementHitCount(value, LocalDate.now(), hitCount.longValue());
				}
			);
			redisService.deleteCounter(postId);
		});
	}
}
