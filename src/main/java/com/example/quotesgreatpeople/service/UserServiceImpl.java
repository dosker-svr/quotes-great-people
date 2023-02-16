package com.example.quotesgreatpeople.service;

import com.example.quotesgreatpeople.dto.UserDto;
import com.example.quotesgreatpeople.entity.UserEntity;
import com.example.quotesgreatpeople.mapper.UserMapper;
import com.example.quotesgreatpeople.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("'id' argument to get the user is 'null'.");
        }
        final Optional<UserEntity> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            throw new NullPointerException("User with id='" + id + "' not found.");
        }

        return UserMapper.INSTANCE.toUserDto(optUser.get());
    }

    public UserEntity getUserEntityByName(String userName) {
        final Optional<UserEntity> optionalUser = userRepository.findByName(userName);
        if (optionalUser.isEmpty()) {
            throw new NullPointerException("User with name='" + userName + "' not found.");
        }
        return optionalUser.get();
    }

    public UserDto createUser(UserDto user) {
        if (user.getPassword() == null) {
            throw new NullPointerException("User with email='" + user.getEmail() + "' don't has a password");
        }

        user.setCreationDate(LocalDateTime.now());
        final UserEntity userEntity = UserMapper.INSTANCE.toUserEntity(user);
        if (userEntity == null) {
            throw new NullPointerException("An error occurred after mapping user.");
        }

        return UserMapper.INSTANCE.toUserDto(userRepository.save(userEntity));
    }
}
