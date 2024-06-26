package ai.jamin.post.service;

import static ai.jamin.exception.Code.*;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import ai.jamin.post.domain.Post;
import ai.jamin.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public Post create(Post post) {
		return postRepository.save(post);
	}

	public Post getById(Long id) {
		Post post = postRepository.findById(id)
			.orElseThrow(() -> new NoSuchElementException(NO_SUCH_ELEMENT.getMessage()));
		post.incrementHitCount(1L);
		return post;
	}
}
