package com.lf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LfVueBlogApplication {

        public static void main(String[] args) {
                SpringApplication.run(LfVueBlogApplication.class, args);
        }

}
