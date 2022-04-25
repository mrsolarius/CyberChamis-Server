package fr.litopia.Integrateur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"fr.litopia.Integrateur.model"})
@EnableJpaRepositories(basePackages = {"fr.litopia.Integrateur.repository"})
public class RestServer {

	public static void main(String[] args) {
		SpringApplication.run(RestServer.class, args);
	}
}
