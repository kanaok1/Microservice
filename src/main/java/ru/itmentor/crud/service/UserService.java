package ru.itmentor.crud.service;

import ru.itmentor.crud.dto.UserDTO;
import ru.itmentor.crud.model.User;

import java.util.List;


public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long userId);
    void saveUser(UserDTO userDTO);
    void updateUser(Long userId, UserDTO userDTO);
    void deleteUser(Long userId);

}
