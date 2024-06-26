package ai.jamin.domain.post.dto.request;

import ai.jamin.post.domain.Post;
import lombok.Builder;

@Builder
public record PostCreateRequest(
	String title,
	String content
) {
	public Post toEntity(){
		return Post.builder()
			.title(title)
			.content(content)
			.build();
	}
}
