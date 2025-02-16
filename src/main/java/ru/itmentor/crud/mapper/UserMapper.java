package ru.itmentor.crud.mapper;

import org.springframework.stereotype.Component;
import ru.itmentor.crud.dto.UserDTO;
import ru.itmentor.crud.model.User;

@Component
public class UserMapper {
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        return user;
    }
}
