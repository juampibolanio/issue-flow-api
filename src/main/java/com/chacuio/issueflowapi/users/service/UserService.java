package com.chacuio.issueflowapi.users.service;

import com.chacuio.issueflowapi.users.dto.UserDTO;
import com.chacuio.issueflowapi.users.dto.UserRequestDTO;

import java.util.UUID;

public interface UserService {
    public UserDTO create(UserRequestDTO dto);
    public UserDTO findById(UUID id);
}
