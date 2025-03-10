package az.idrak.appv1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppV1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AppV1Application.class, args);
	}

	@Override
	public void run(String[] args) throws Exception{
		log.trace("Application started");
	}
}