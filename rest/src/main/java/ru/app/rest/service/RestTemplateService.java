package ru.app.rest.service;

import ru.app.rest.dto.User;

public interface RestTemplateService {
    String saveUser(User user);
    String updateUser(User user);
    String deleteUser(Long id);
}
