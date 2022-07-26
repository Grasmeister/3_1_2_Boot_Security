package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.UserInfo;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


//    @GetMapping
//    public String index(Model model) {
//        model.addAttribute("users", userService.listUsers());
//        return "user";
//    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roles", roleService.listRoles());
//        model.addAttribute("roles", roleService.getRoleByID(userService.findUserById(id).getRoles().))
        return "/view";
    }

//    @GetMapping("/createUser")
//    public String creatUserForm(Model model) {
//        model.addAttribute("user", new UserInfo());
//        model.addAttribute("roles", roleService.listRoles());
//
//        return "/createUser";
//    }

//    @PostMapping("/createUser")
//    public String createUser(@ModelAttribute("user") UserInfo user) {
//        userService.add(user);
//        return "redirect:/user";
//    }

//    @GetMapping("/{id}/updateUser")
//    public String updateUser(Model model, @PathVariable("id") Long id) {
//        model.addAttribute("user", userService.findUserById(id));
//        return "/updateUser";
//
//    }

//    @PatchMapping("/{id}")
//    public String edit(@ModelAttribute("user") UserInfo user, @PathVariable("id") Long id) {
//        userService.updateUserById(id, user);
//        return "redirect:/";
//    }

//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") Long id) {
//        userService.removeUserById(id);
//        return "redirect:/";
//    }

}
