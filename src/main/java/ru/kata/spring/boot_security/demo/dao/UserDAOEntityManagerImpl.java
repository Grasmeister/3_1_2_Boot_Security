//package ru.kata.spring.boot_security.demo.dao;
//
//
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
//@Component
//public class UserDAOEntityManagerImpl implements UserDAO {
//
//    @PersistenceContext
//    private EntityManager entityManager;
////    @Autowired
////    private BCryptPasswordEncoder bCryptPasswordEncoder;
////    @Autowired
////    public UserDAOEntityManagerImpl(BCryptPasswordEncoder bCryptPasswordEncoder){
////        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
////    }
//
//
//    @Override
//    public void addUser(User user) {
//
//        entityManager.persist(user);
////        return true;
//    }
//
//    @Override
//    public List<User> listUsers() {
//        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
//    }
//
//    @Override
//    public User findUserById(Long id) {
//        return entityManager.find(User.class, id);
//    }
//
//    @Override
//    public User getUserByLogin(String userlogin) throws UsernameNotFoundException {
//        List<User> userInfos = entityManager.createQuery("SELECT u FROM User u where u.login = :userlogin",
//                User.class).getResultList();
//        if (userInfos.size() == 0) {
//            throw new UsernameNotFoundException("User not found");
//        }
//        User user = userInfos.get(0);
//        return user;
//    }
//
//    @Override
//    public void updateUserById(Long id, User updateuser) {
//        User anotherUser = findUserById(id);
//        anotherUser.setName(updateuser.getName());
//        anotherUser.setLastName(updateuser.getLastName());
//        anotherUser.setAge(updateuser.getAge());
//        anotherUser.setHeight(updateuser.getHeight());
//        anotherUser.setWeight(updateuser.getWeight());
//        entityManager.merge(anotherUser);
//    }
//
//
//    @Override
//    public void removeUserById(Long id) {
//        entityManager.remove(findUserById(id));
//    }
//
//}
