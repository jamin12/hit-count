package ai.jamin.post.service;

import static ai.jamin.post.service.PostFixture.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ai.jamin.common.AcceptanceTest;
import ai.jamin.post.domain.Post;
import ai.jamin.post.domain.PostDailyHits;
import ai.jamin.post.repository.PostDailyHitsRepository;
import ai.jamin.post.repository.PostRepository;

@Transactional
class PostDailyHitsServiceTest extends AcceptanceTest {
	@Autowired
	private PostDailyHitsRepository postDailyHitsRepository;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostDailyHitsService postDailyHitsService;

	@Test
	void 오늘조회수를_올린다() {
		// given
		Post 게시글_생성됨 = postRepository.save(게시글_생성(0L));
		postDailyHitsRepository.save(
			PostDailyHits.builder().post(게시글_생성됨).day(LocalDate.now()).build());

		// when
		PostDailyHits postDailyHits = postDailyHitsService.incrementHitCount(게시글_생성됨, LocalDate.now());

		// then
		assertThat(postDailyHits.getHitCount()).isEqualTo(2L);
	}

	@Test
	void 오늘_조회수가_없으면_생성후_올린다() {
		// given
		Post 게시글_생성됨 = postRepository.save(게시글_생성(0L));

		// when
		PostDailyHits postDailyHits = postDailyHitsService.incrementHitCount(게시글_생성됨, LocalDate.now());

		// then
		assertThat(postDailyHits.getHitCount()).isEqualTo(1L);
	}
}