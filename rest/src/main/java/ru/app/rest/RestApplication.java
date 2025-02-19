package ru.app.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.app.rest.dto.User;
import ru.app.rest.service.RestTemplateService;

@SpringBootApplication
@Slf4j
public class RestApplication {

    private final RestTemplateService restTemplateService;

    public RestApplication(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            User user = new User(3L, "James", "Brown", (byte) 16);

            log.info(restTemplateService.saveUser(user));
            user.setName("Thomas");
            user.setLastName("Shelby");
            log.info(restTemplateService.updateUser(user));
            log.info(restTemplateService.deleteUser(3L));
        };
    }
}
