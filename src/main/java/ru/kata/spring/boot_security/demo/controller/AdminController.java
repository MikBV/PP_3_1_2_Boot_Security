package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping(value = "pages")
public class AdminController {

    private final UserRepository userRepository;

    @Autowired
    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }


    @GetMapping(value = "/admin")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin";
    }

    /**
     * Маппинги для добавляения нового пользователя
     */

    @GetMapping(value = "/admin/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "/admin/new")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "changeconfirm";
    }

    /**
     * Мапы для изменения пользователя
     */

    @GetMapping(value = "/admin/change/id")
    public String changeUserById(@RequestParam(value = "id", required = true) Long id, Model model) {
        Optional<User> optiUser = userRepository.findById(id);
        User user = optiUser.orElse(null);
        model.addAttribute("user", user);
        return "change";
    }

    @PostMapping(value = "/admin/change/id")
    public String saveChangesInUser(@ModelAttribute User user, @PathVariable Long id) {
        userRepository.save(user);
        return "changeconfirm";
    }

    /**
     * Маппинги для удаления
     */

    @GetMapping(value = "/admin/delete/id")
    public String deleteUserById(@RequestParam(value = "id", required = true) Long id, Model model) {
        Optional<User> optiUser = userRepository.findById(id);
        User user = optiUser.orElse(null);
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping(value = "/admin/delete/id")
    public String deletingUserById(@RequestParam(value = "id", required = true) Long id) {
        userRepository.deleteById(id);
        return "changeconfirm";
    }
}
