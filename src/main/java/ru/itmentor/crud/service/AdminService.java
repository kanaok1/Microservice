package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.request.CreateUser;
import ru.itmentor.crud.dto.request.UpdateUser;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.model.User;

import java.util.List;


public interface AdminService {
    List<FindUserResponse> findAllUsers();
    FindUserResponse findUserById(Long userId);
    void saveUser(CreateUser userDTO);
    void updateUser(Long userId, UpdateUser userDTO);
    void deleteUser(Long userId);
    User findByUsername(String username);
}
