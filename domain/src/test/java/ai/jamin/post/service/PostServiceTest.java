package ai.jamin.post.service;

import static ai.jamin.post.service.PostFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ai.jamin.common.AcceptanceTest;
import ai.jamin.post.domain.Post;
import ai.jamin.post.repository.PostRepository;

@Transactional
class PostServiceTest extends AcceptanceTest {
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private PostService postService;

	@Test
	void 조회를하면_조회수가_1_오른다() {
		// given
		Post 게시글_생성 = 게시글_생성(0L);
		Post 게시글_생성됨 = postRepository.save(게시글_생성);

		// when
		Post 게시글_조회됨 = postService.getById(게시글_생성됨.getId());

		// then
		assertThat(게시글_조회됨.getHitCount()).isEqualTo(1L);
	}
}