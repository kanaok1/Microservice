package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.response.FindUserResponse;

public interface UserService {
    FindUserResponse getCurrentUser();
}
