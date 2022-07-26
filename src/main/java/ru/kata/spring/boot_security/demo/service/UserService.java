package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.UserInfo;

import java.util.List;

public interface UserService {

    void add(UserInfo user);

    List<UserInfo> listUsers();

    void updateUserById(Long id, UserInfo user);

    void removeUserById(Long id);

    UserInfo findUserById(Long id);

    UserInfo getUserByLogin(String userlogin);
}
