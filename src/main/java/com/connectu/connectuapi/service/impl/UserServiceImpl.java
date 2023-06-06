package com.connectu.connectuapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.connectu.connectuapi.controller.Code;
import com.connectu.connectuapi.dao.UserDao;
import com.connectu.connectuapi.domain.User;
import com.connectu.connectuapi.exception.BusinessException;
import com.connectu.connectuapi.service.IUserService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Locale;

@Transactional
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;
    public void addFakeUsers(int count) {
        for (int i = 0; i < count; i++) {
            User fakeUser = UserServiceImpl.createFakeUser();
            userDao.insert(fakeUser);
        }
    }
    public static User createFakeUser() {
        Faker faker = new Faker(new Locale("zh-CN"));
        User user = new User();
        String username = faker.internet().password();
        user.setEmail(faker.internet().emailAddress(username));
        user.setPassword(faker.internet().password());
        user.setUserName(faker.name().name());
        return user;
    }
    @Override
    public User getById(Serializable id) {
        if((Integer)id==1) {
            throw new BusinessException(Code.BUSINESS_ERR, "id==1 exception");
        }
        return super.getById(id);
    }
}
