package ru.itmentor.crud.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.crud.dto.response.FindUserResponse;
import ru.itmentor.crud.exception.model.UserNotFoundException;
import ru.itmentor.crud.mapper.UserMapper;
import ru.itmentor.crud.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public FindUserResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userMapper.toFindUserResponseDTOFromEntity(userRepository.findByUsername(username)
                .orElseThrow(UserNotFoundException::new));
    }
}
