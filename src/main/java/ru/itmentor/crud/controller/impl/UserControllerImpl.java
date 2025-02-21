package ru.itmentor.crud.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.itmentor.crud.controller.api.UserController;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Autowired
    public UserControllerImpl (UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindUserResponse getInformationAboutMe() {
        return userService.getCurrentUser();
    }
}
