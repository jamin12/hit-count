package ai.jamin.post.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(uniqueConstraints = {
	@UniqueConstraint(columnNames = {"post_id", "day"})
})
public class PostDailyHits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;
	private LocalDate day;
	private Long hitCount;

	@Builder
	public PostDailyHits(LocalDate day, Long id, Post post) {
		this.day = day;
		this.hitCount = 1L;
		this.id = id;
		this.post = post;
	}

	public void incrementHitCount(Long hitCount) {
		this.hitCount += hitCount;
	}
}
