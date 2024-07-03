package ai.jamin.domain.post.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.jamin.domain.post.dto.request.PostCreateRequest;
import ai.jamin.domain.post.dto.response.PostResponse;
import ai.jamin.post.domain.Post;
import ai.jamin.post.service.PostService;
import ai.jamin.service.RedisService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostAppService {
	private final PostService postService;
	private final RedisService redisService;

	@Transactional
	public void cratePost(PostCreateRequest postCreateRequest) {
		postService.create(postCreateRequest.toEntity());
	}

	public PostResponse getPostById(Long postId) {
		Post post = postService.getById(postId);
		redisService.incrementCount(postId);
		return PostResponse.from(post);
	}
}
