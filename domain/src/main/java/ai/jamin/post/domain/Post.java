package ai.jamin.post.domain;

import org.hibernate.annotations.Comment;

import ai.jamin.common.domain.MutableBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post extends MutableBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Comment("제목")
	private String title;
	@Comment("내용")
	private String content;
	@Comment("조회수")
	private Long hitCount;

	@Builder
	public Post(Long id, String content, String title) {
		this.id = id;
		this.content = content;
		this.title = title;
		this.hitCount = 0L;
	}

	public void incrementHitCount(Long hitCount) {
		this.hitCount += hitCount;
	}
}
