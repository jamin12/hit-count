package ai.jamin.post.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import ai.jamin.post.domain.Post;
import ai.jamin.post.domain.PostDailyHits;
import ai.jamin.post.repository.PostDailyHitsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostDailyHitsService {
	private final PostDailyHitsRepository postDailyHitsRepository;

	public PostDailyHits create(PostDailyHits postDailyHits) {
		return postDailyHitsRepository.save(postDailyHits);
	}

	public PostDailyHits incrementHitCount(Post post, LocalDate day) {
		return postDailyHitsRepository.findByPostIdAndDay(post.getId(), day)
			.map(postDailyHits -> {
				postDailyHits.incrementHitCount(1L);
				return postDailyHits;
			})
			.orElseGet(() -> create(
				PostDailyHits.builder().post(post).day(day).build()));
	}
}
