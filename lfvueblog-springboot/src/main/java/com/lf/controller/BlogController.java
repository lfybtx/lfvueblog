package com.lf.ctroller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lf.common.R;
import com.lf.domain.Blog;
import com.lf.domain.LoginUser;
import com.lf.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogCtroller {

        @Autowired
        private BlogService blogService;

        /**
         * 查询所有博客文章
         * @param currentPage
         * @return
         */
        @GetMapping("/blogs")
        public R blogs(Integer currentPage){
                if(currentPage==null||currentPage<1) {
                        currentPage=1;
                }
                Page<Blog> page=new Page<>(currentPage,5);
                LambdaQueryWrapper queryWrapper=new LambdaQueryWrapper();
                queryWrapper.orderByDesc("created");
                blogService.page(page, queryWrapper);
                return R.success(page);
        }

        /**
         * 查询单篇博客
         */
        @DeleteMapping("/{id}")
        public R detail(@PathVariable Long id){
                Blog blog = blogService.getById(id);
                Assert.notNull(blog,"该博客已删除！");
                return R.success(blog);
        }

        /**
         * 修改博客
         */
        @Transactional
        @PostMapping("/edit")
        public R edit(@Validated @RequestBody Blog blog){
                //获取当前用户信息
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                LoginUser principal = (LoginUser) authentication.getPrincipal();
                Long loginId = principal.getUser().getId();
                if(loginId.equals(blog.getUserId())){
                        return R.success(200,"当前用户无法修改此文章",null);
                }
                blogService.save(blog);
                return R.success(200,"修改成功",blog);
        }
}
