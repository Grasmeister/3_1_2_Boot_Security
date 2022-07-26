package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.UserInfo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDao;

    @Autowired
    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void add(UserInfo user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public List<UserInfo> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public void updateUserById(Long id, UserInfo updateuser) {
        userDao.updateUserById(id, updateuser);
    }

    @Override
    @Transactional
    public UserInfo findUserById(Long id) {
        return userDao.findUserById(id);
    }

    @Override
    public UserInfo getUserByLogin(String userlogin) {
        return userDao.getUserByLogin(userlogin);
    }

    @Override
    @Transactional
    public void removeUserById(Long id) {
        userDao.removeUserById(id);
    }

}
