package com.lf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lf.dao.UserDao;
import com.lf.domain.LoginUser;
import com.lf.domain.User;
import com.lf.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService , UserDetailsService {
        @Override
        public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                //查询用户信息
                LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
                queryWrapper.eq(User::getUsername,s);
                User user = getOne(queryWrapper);
                if(Objects.isNull(user)){
                        throw new RuntimeException("用户名或密码错误");
                }
                //TODO 查询对应权限信息
                //封装成UserDetails对象返回
                return new LoginUser(user);
        }
}
