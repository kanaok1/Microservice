package ru.itmentor.crud.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crud.controller.api.AdminController;
import ru.itmentor.crud.dto.UserDTO;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;
    private static final String RELOAD = "redirect:/admin";

    @Autowired
    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @Override
    public String getAllUsers(Model model) {
        model.addAttribute("users", adminService.findAllUsers());
        model.addAttribute("userDTO", new UserDTO());
        return "admin";
    }

    @GetMapping("/search")
    @Override
    public String getUserById(@RequestParam(value = "id") Long id, Model model) {
        User user = adminService.findUserById(id);
        model.addAttribute("foundUser", user);
        return "find-result";
    }

    @PostMapping
    @Override
    public String addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        adminService.saveUser(userDTO);
        return RELOAD;
    }

    @DeleteMapping
    @Override
    public String deleteUser(@RequestParam("id") Long userId) {
        adminService.deleteUser(userId);
        return RELOAD;
    }

    @PutMapping
    @Override
    public String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDTO userDTO) {
        adminService.updateUser(userId, userDTO);
        return RELOAD;
    }
}
