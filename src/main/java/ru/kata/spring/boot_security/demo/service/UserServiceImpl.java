package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserInfoRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //    private UserDAO userDao;
    private UserInfoRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserInfoRepository userRepository) {
        this.userRepository = userRepository;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public boolean add(User user) {

//        User userInfoFromDB = getUserByLogin(user.getLogin());
        User userInfoFromDB = userRepository.findByUserlogin(user.getLogin());
        if (userInfoFromDB != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        userDao.addUser(userInfoFromDB);
        return true;
    }

    @Override
    public List<User> listUsers() {
//        return userDao.listUsers();
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void updateUserById(Long id, User updateuser) {
        User user = userRepository.getById(id);
        user.setAge(updateuser.getAge());
        user.setHeight(updateuser.getHeight());
        user.setLastName(updateuser.getLastName());
        user.setName(updateuser.getName());
        user.setRoles(updateuser.getRoles());
        user.setPassword(bCryptPasswordEncoder.encode(updateuser.getPassword()));
//        userDao.updateUserById(id, updateuser);
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.getById(id);
//        return userDao.findUserById(id);
    }


    @Override
    @Transactional
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
//        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userLogin) throws UsernameNotFoundException {
        User user = userRepository.findByUserlogin(userLogin);
//        User user = userDao.getUserByLogin(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.getAuthorities());
    }

}
