package ru.itmentor.crud.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itmentor.crud.controller.api.LoginController;

@Controller
public class LoginControllerImpl implements LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
