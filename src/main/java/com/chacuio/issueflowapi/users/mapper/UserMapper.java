package com.chacuio.issueflowapi.users.mapper;

import com.chacuio.issueflowapi.users.dto.UserDTO;
import com.chacuio.issueflowapi.users.dto.UserRequestDTO;
import com.chacuio.issueflowapi.users.dto.UserSummaryDTO;
import com.chacuio.issueflowapi.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public User toEntity(UserRequestDTO dto) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password()) // the password here should be hashed. In the future, implement a password encoder
                .role(dto.role())
                .build();
    }

    public UserSummaryDTO toSummary(User user) {
        if (user == null) {
            return null;
        }
        return new UserSummaryDTO(
                user.getId(),
                user.getName(),
                user.isActive()
        );
    }
}
