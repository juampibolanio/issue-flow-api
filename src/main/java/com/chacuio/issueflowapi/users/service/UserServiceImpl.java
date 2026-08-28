package com.chacuio.issueflowapi.users.service;

import com.chacuio.issueflowapi.users.exception.EmailAlreadyExistsException;
import com.chacuio.issueflowapi.users.dto.UserDTO;
import com.chacuio.issueflowapi.users.dto.UserRequestDTO;
import com.chacuio.issueflowapi.users.exception.UserNotFoundException;
import com.chacuio.issueflowapi.users.mapper.UserMapper;
import com.chacuio.issueflowapi.users.model.User;
import com.chacuio.issueflowapi.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public UserDTO create(UserRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        User mappedUser = mapper.toEntity(dto);

        User savedUser = repository.save(mappedUser);

        return mapper.toDto(savedUser);
    }

    @Override
    public UserDTO findById(UUID id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return mapper.toDto(user);
    }
}
