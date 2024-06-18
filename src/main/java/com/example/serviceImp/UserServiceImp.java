package com.example.serviceImp;

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> saveAll(List<User> users) {
        int count = userDao.saveAll(users);
        return users;
    }



}
