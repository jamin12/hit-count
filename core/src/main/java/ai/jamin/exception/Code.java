package ai.jamin.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Code {
	NO_SUCH_ELEMENT("요소를 찾을 수 없습니다.");
	private final String message;
}
