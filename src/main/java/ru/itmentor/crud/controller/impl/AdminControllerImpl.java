package ru.itmentor.crud.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.crud.controller.api.UserPanelController;
import ru.itmentor.crud.dto.request.CreateUserDTO;
import ru.itmentor.crud.dto.request.UpdateUserDTO;
import ru.itmentor.crud.dto.response.FindUserResponseDTO;
import ru.itmentor.crud.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminControllerImpl implements UserPanelController {

    private final AdminService adminService;

    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<FindUserResponseDTO> getAllUsers() {
        return adminService.findAllUsers();
    }

    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public FindUserResponseDTO getUserById(@PathVariable Long id) {
        return adminService.findUserById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody CreateUserDTO userDTO) {
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
    public void updateUser(@PathVariable Long id, @RequestBody UpdateUserDTO userDTO) {
        adminService.updateUser(id, userDTO);
    }
}
