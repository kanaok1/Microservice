package ru.itmentor.crud.controller.api;

import ru.itmentor.crud.dto.response.FindUserResponseDTO;

public interface UserController {
    FindUserResponseDTO getInformationAboutMe();
}
