package com.transferwise.fetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@PropertySource("classpath:config.properties")
@SpringBootApplication(scanBasePackages = {"com.transferwise.fetcher"})
@EntityScan("com.transferwise.fetcher.entity")
@EnableJpaRepositories("com.transferwise.fetcher.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
