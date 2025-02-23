package ru.itmentor.crud.controller.api;

import ru.itmentor.crud.dto.response.FindUserResponse;

public interface UserController {
    FindUserResponse getInformationAboutMe();
}
