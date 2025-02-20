package ru.itmentor.crud.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itmentor.crud.controller.api.UserController;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl (UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    public String getUserInfo(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user-profile";
    }
}
