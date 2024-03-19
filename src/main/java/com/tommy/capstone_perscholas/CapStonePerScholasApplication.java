package com.tommy.capstone_perscholas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CapStonePerScholasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapStonePerScholasApplication.class, args);
    }

}
