package com.connectu.connectuapi.dao;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.connectu.connectuapi.domain.User;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserDao extends BaseMapper<User> {
}
