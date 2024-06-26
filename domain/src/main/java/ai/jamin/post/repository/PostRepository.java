package ai.jamin.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.jamin.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
