package ru.itmentor.crud.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.crud.dto.UserDTO;
import ru.itmentor.crud.exception.model.UserNotFoundException;
import ru.itmentor.crud.mapper.UserMapper;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.repository.AdminRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAllUsers() {
        return adminRepository.findAll();
    }

    @Override
    public User findUserById(Long userId) {
        return adminRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
    }

    @Transactional
    @Override
    public void saveUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        adminRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        adminRepository.delete(findUserById(userId));
    }

    @Transactional
    @Override
    public void updateUser(Long userId, UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setId(findUserById(userId).getId());
        adminRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

}
