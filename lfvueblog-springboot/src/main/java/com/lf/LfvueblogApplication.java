package com.lf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LfvueblogApplication {

        public static void main(String[] args) {
                SpringApplication.run(LfvueblogApplication.class, args);
        }

}
