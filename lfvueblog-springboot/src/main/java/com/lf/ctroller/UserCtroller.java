package com.lf.ctroller;

import com.lf.common.R;
import com.lf.domain.User;
import com.lf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserCtroller {
        @Autowired
        private UserService userService;

        @GetMapping("/index")
        public R index(){
                User user = userService.getById(1L);
                return R.success(user);
        }

        @PostMapping("/save")
        public R save(@RequestBody User user){
                return R.success(user);
        }
}
