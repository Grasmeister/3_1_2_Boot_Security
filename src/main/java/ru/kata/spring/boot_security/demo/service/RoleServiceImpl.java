package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    //    private RoleDAO roleDAO;
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void addRole(Role role) {
//        roleDAO.addRole(role);
        roleRepository.save(role);
    }

    @Override
    @Transactional
    public List<Role> listRoles() {
//        return roleDAO.listRoles();
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByID(Long id) {
//        return roleDAO.getRoleByID(id);
        return roleRepository.getById(id);
    }
}
