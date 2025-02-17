package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.request.CreateUserDTO;
import ru.itmentor.crud.dto.request.UpdateUserDTO;
import ru.itmentor.crud.dto.response.FindUserResponseDTO;
import ru.itmentor.crud.model.User;

import java.util.List;


public interface AdminService {
    List<FindUserResponseDTO> findAllUsers();
    FindUserResponseDTO findUserById(Long userId);
    void saveUser(CreateUserDTO userDTO);
    void updateUser(Long userId, UpdateUserDTO userDTO);
    void deleteUser(Long userId);
    User findByUsername(String username);
}
