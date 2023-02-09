package com.lf.ctroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
public class HelloCtroller {

        @GetMapping("/hello")
        public void hello(){
                System.out.println("hello");
        }
}
