package ru.itmentor.crud.dto.response;

import lombok.Data;
import ru.itmentor.crud.model.Role;

import java.util.List;

@Data
public class FindUserResponseDTO {
    private Long id;
    private String username;
    private String password;
    private Integer phone;
    private List<Role> roles;
}
