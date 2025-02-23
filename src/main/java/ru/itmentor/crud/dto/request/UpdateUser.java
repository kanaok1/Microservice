package ru.itmentor.crud.dto.request;

import lombok.Data;

@Data
public class UpdateUser {
    private String username;
    private String password;
    private Boolean sex;
    private Integer phone;
}
