package ru.itmentor.crud.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crud.controller.api.AdminController;
import ru.itmentor.crud.dto.request.CreateUser;
import ru.itmentor.crud.dto.request.UpdateUser;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerImpl implements AdminController {

    private final AdminService adminService;

    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FindUserResponse> getAllUsers() {
        return adminService.findAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FindUserResponse getUserById(@PathVariable Long id) {
        return adminService.findUserById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody CreateUser userDTO) {
        adminService.saveUser(userDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUser userDTO) {
        adminService.updateUser(id, userDTO);
    }
}
