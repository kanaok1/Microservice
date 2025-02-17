package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.response.FindUserResponseDTO;

public interface UserService {
    FindUserResponseDTO getCurrentUser();
}
