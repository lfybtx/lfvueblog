package com.lf.ctroller;

import com.lf.common.R;
import com.lf.domain.LoginUser;
import com.lf.domain.User;
import com.lf.service.UserService;
import com.lf.util.JwtUtil;
import com.lf.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping
public class LoginCtroller {

        @Autowired
        private UserService userService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private RedisUtil redisUtil;
        @PostMapping ("/login")
        public R login(@RequestBody User user){
//                AuthenticationManager认证
                UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
                Authentication authenticate = authenticationManager.authenticate(token);

//                认证失败 抛出异常
                if(Objects.isNull(authenticate)){
                        throw new RuntimeException("登录失败");
                }
//                认证成功 使用userid生成jwt
                LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
                Long id = loginUser.getUser().getId();
                String jwt = JwtUtil.createJWT(id.toString());
                Map<String ,String> map=new HashMap<>();
                map.put("token",jwt);
//                用户信息存入redis
                redisUtil.set("login:"+id.toString(),loginUser);
//                jwt返回
                return R.success(200,"登录成功",map);
        }
        @GetMapping("/logout")
        public R logout(){
                UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                LoginUser loginUser = (LoginUser) authentication.getPrincipal();
                Long userid = loginUser.getUser().getId();
                redisUtil.del("login:"+userid);
                return R.success(200,"退出成功",null);
        }
}
