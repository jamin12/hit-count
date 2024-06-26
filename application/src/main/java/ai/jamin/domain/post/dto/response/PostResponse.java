package ai.jamin.domain.post.dto.response;

import ai.jamin.post.domain.Post;
import lombok.Builder;

@Builder
public record PostResponse(
	Long id,
	String title,
	String content,
	Long hitCount
) {
	public static PostResponse from(Post post){
		return PostResponse.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.hitCount(post.getHitCount())
			.build();
	}
}