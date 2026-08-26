package com.chacuio.issueflowapi.users.service;

import com.chacuio.issueflowapi.users.dto.UserDTO;
import com.chacuio.issueflowapi.users.dto.UserRequestDTO;

import java.util.UUID;

public interface UserService {
    UserDTO create(UserRequestDTO dto);
    UserDTO findById(UUID id);
}
