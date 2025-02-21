package ru.itmentor.crud.dto.response;

import lombok.Data;
import ru.itmentor.crud.model.Role;

import java.util.Set;

@Data
public class FindUserResponse {
    private Long id;
    private String username;
    private String password;
    private Integer phone;
    private Set<Role> roles;
}
