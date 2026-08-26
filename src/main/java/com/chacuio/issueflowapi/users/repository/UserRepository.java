package com.chacuio.issueflowapi.users.repository;

import com.chacuio.issueflowapi.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
}
