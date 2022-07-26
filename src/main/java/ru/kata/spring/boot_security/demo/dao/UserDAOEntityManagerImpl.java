package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.UserInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UserDAOEntityManagerImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(UserInfo user) {
        entityManager.persist(user);
    }

    @Override
    public List<UserInfo> listUsers() {
        return entityManager.createQuery("SELECT u FROM UserInfo u", UserInfo.class).getResultList();
    }

    @Override
    public UserInfo findUserById(Long id) {
        return entityManager.find(UserInfo.class, id);
    }

    @Override
    public UserInfo getUserByLogin(String userlogin) {
        return entityManager.find(UserInfo.class, userlogin);
    }

    @Override
    public void updateUserById(Long id, UserInfo updateuser) {
        UserInfo anotherUser = findUserById(id);

        anotherUser.setName(updateuser.getName());
        anotherUser.setLastName(updateuser.getLastName());
        anotherUser.setAge(updateuser.getAge());
        anotherUser.setHeight(updateuser.getHeight());
        anotherUser.setWeight(updateuser.getWeight());

        entityManager.merge(anotherUser);
    }


    @Override
    public void removeUserById(Long id) {
        entityManager.remove(findUserById(id));
    }

}
