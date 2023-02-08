package com.lf.ctroller;

import com.lf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserCtroller {
        @Autowired
        private UserService userService;

        @GetMapping("/{id}")
        public Object test(@PathVariable Long id){
                return userService.getById(id);
        }
}
