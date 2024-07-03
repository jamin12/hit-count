package ai.jamin.post.service;

import static ai.jamin.exception.Code.*;

import java.util.NoSuchElementException;
import java.util.Optional;

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
		return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException(NO_SUCH_ELEMENT.getMessage()));
	}

	public Optional<Post> getOptionalById(Long id) {
		return postRepository.findById(id);
	}
}
