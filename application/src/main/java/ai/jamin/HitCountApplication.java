package ai.jamin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HitCountApplication {
	public static void main(String[] args) {
		SpringApplication.run(HitCountApplication.class, args);
	}
}
