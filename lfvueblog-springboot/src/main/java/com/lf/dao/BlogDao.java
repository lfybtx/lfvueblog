package com.lf.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lf.domain.Blog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogDao extends BaseMapper<Blog> {
}
