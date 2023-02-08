package com.lf;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class LfvueblogApplicationTests {


        @Autowired
        private PasswordEncoder passwordEncoder;
        @Test
        void contextLoads() {
                String encode = passwordEncoder.encode("123456");
                log.info(encode);
        }

}
