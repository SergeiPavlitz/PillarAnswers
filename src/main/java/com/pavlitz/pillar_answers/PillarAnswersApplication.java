package com.pavlitz.pillar_answers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.pavlitz.pillar_answers")
public class PillarAnswersApplication {

    public static void main(String[] args) {
        SpringApplication.run(PillarAnswersApplication.class, args);
    }

}
