package com.lachguer.pfabck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lachguer.pfabck.repository")
@EntityScan(basePackages = "com.lachguer.pfabck.model")
public class PfaBckApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfaBckApplication.class, args);
    }

}
