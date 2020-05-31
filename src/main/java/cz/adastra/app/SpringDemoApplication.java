package cz.adastra.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("cz.adastra.*")
@ComponentScan("cz.adastra.*")
@EntityScan("cz.adastra.*")
public class SpringDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoApplication.class, args);
  }

}
