package ru.itmentor.crud.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crud.controller.api.AdminPanelController;
import ru.itmentor.crud.dto.UserDTO;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminPanelControllerImpl implements AdminPanelController {

    private final UserService userService;
    private static final String RELOAD = "redirect:/admin";

    @Autowired
    public AdminPanelControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Override
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("userDTO", new UserDTO());
        return "admin";
    }

    @GetMapping("/search")
    @Override
    public String getUserById(@RequestParam(value = "id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("foundUser", user);
        return "find-result";
    }

    @PostMapping
    @Override
    public String addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.saveUser(userDTO);
        return RELOAD;
    }

    @DeleteMapping
    @Override
    public String deleteUser(@RequestParam("id") Long userId) {
        userService.deleteUser(userId);
        return RELOAD;
    }

    @PutMapping
    @Override
    public String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDTO userDTO) {
        userService.updateUser(userId, userDTO);
        return RELOAD;
    }
}
