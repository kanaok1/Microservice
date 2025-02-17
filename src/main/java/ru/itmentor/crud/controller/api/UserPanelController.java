package ru.itmentor.crud.controller.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmentor.crud.dto.UserDTO;


public interface UserPanelController {
    String getAllUsers(Model model);
    String getUserById(@RequestParam(value = "id") Long id, Model model);
    String addUser(@ModelAttribute("userDTO") UserDTO userDTO);
    String deleteUser(@RequestParam("id") Long userId);
    String updateUser(@RequestParam("id") Long userId, @ModelAttribute("user") UserDTO userDTO);

}
