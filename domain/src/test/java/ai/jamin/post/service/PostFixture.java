package ai.jamin.post.service;

import static org.instancio.Select.*;

import org.instancio.Instancio;

import ai.jamin.post.domain.Post;

public class PostFixture {
	private PostFixture() {
	}

	public static Post 게시글_생성(Long hitCount) {
		return Instancio.of(Post.class)
			.set(field(Post::getHitCount), hitCount)
			.create();
	}
}
