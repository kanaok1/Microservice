package ru.itmentor.crud.controller.api;

import ru.itmentor.crud.dto.request.CreateUserDTO;
import ru.itmentor.crud.dto.request.UpdateUserDTO;
import ru.itmentor.crud.dto.response.FindUserResponseDTO;

import java.util.List;


public interface UserPanelController {
    List<FindUserResponseDTO> getAllUsers();
    FindUserResponseDTO getUserById(Long id);
    void addUser(CreateUserDTO userDTO);
    void deleteUser(Long userId);
    void updateUser(Long userId, UpdateUserDTO userDTO);
}
