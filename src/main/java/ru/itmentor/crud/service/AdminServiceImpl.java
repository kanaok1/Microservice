package ru.itmentor.crud.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.crud.dto.request.CreateUser;
import ru.itmentor.crud.dto.request.UpdateUser;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.exception.model.UserNotFoundException;
import ru.itmentor.crud.mapper.UserMapper;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.repository.UserRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;

    }

    @Transactional
    @Override
    public List<FindUserResponse> findAllUsers() {
        return userMapper.toDtoFindListFromEntityList(userRepository.findAll());
    }

    @Transactional
    @Override
    public FindUserResponse findUserById(Long userId) {
        return userMapper.toFindUserResponseDTOFromEntity(userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        ));
    }

    @Transactional
    @Override
    public void saveUser(CreateUser userDTO) {
        User user = userMapper.toEntityFromCreateDto(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Transactional
    @Override
    public void updateUser(Long userId, UpdateUser userDTO) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
        userMapper.updateEntityFromDto(userDTO, user);
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
