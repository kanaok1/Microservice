package ru.itmentor.crud.controller.api;

import ru.itmentor.crud.dto.request.CreateUser;
import ru.itmentor.crud.dto.request.UpdateUser;
import ru.itmentor.crud.dto.response.FindUserResponse;

import java.util.List;


public interface AdminController {
    List<FindUserResponse> getAllUsers();
    FindUserResponse getUserById(Long id);
    void addUser(CreateUser userDTO);
    void deleteUser(Long userId);
    void updateUser(Long userId, UpdateUser userDTO);
}
