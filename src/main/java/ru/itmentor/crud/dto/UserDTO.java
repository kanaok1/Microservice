package ru.itmentor.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
    private Boolean sex;
    private Integer phone;
}
