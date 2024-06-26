package ai.jamin.domain.post.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ai.jamin.domain.post.dto.request.PostCreateRequest;
import ai.jamin.domain.post.dto.response.PostResponse;
import ai.jamin.domain.post.service.PostAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/posts")
@Tag(name = "게시글 API")
@RequiredArgsConstructor
public class PostRestController {
	private final PostAppService postAppService;

	@PostMapping
	public ResponseEntity<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
		postAppService.cratePost(postCreateRequest);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
		PostResponse postResponse = postAppService.getPostById(postId);
		return ResponseEntity.ok(postResponse);
	}
}
