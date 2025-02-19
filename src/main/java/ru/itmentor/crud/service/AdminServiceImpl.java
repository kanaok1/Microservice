package ru.itmentor.crud.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.crud.dto.request.CreateUserDTO;
import ru.itmentor.crud.dto.request.UpdateUserDTO;
import ru.itmentor.crud.dto.response.FindUserResponseDTO;
import ru.itmentor.crud.exception.model.UserNotFoundException;
import ru.itmentor.crud.mapper.UserMapper;
import ru.itmentor.crud.model.User;
import ru.itmentor.crud.repository.UserRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AdminServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public List<FindUserResponseDTO> findAllUsers() {
        return userMapper.toDtoFindListFromEntityList(userRepository.findAll());
    }

    @Transactional
    @Override
    public FindUserResponseDTO findUserById(Long userId) {
        return userMapper.toFindUserResponseDTOFromEntity(userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        ));
    }

    @Transactional
    @Override
    public void saveUser(CreateUserDTO userDTO) {
        userRepository.save(userMapper.toEntityFromCreateDto(userDTO));
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
    public void updateUser(Long userId, UpdateUserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(
                UserNotFoundException::new
        );
        userMapper.updateEntityFromDto(userDTO, user);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

}
