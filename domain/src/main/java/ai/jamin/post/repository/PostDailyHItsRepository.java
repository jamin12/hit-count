package ai.jamin.post.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.jamin.post.domain.PostDailyHits;

public interface PostDailyHItsRepository extends JpaRepository<PostDailyHits, Long> {
	Optional<PostDailyHits> findByPostIdAndDay(Long postId, LocalDate day);
}
