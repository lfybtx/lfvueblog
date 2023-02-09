package com.lf.filter;

import cn.hutool.extra.mail.UserPassAuthenticator;
import com.lf.domain.LoginUser;
import com.lf.util.JwtUtil;
import com.lf.util.RedisUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter{

        @Autowired
        private RedisUtil redisUtil;
        @Override
        protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//                获取token
                String token = httpServletRequest.getHeader("token");
                if(!StringUtils.hasText(token)){
                      filterChain.doFilter(httpServletRequest,httpServletResponse);
                      return;
                }
//                解析token
                String userid;
                try {
                        final Claims claims = JwtUtil.parseJWT(token);
                         userid = claims.getSubject();

                } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException("token非法");
                }
//                redis中获取用户信息
                LoginUser user = (LoginUser) redisUtil.get("login" + userid);
                if(Objects.isNull(user)){
                        throw new RuntimeException("用户未登录");
                }
//              存入SecurityContextHolder中
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                放行
                filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
}
