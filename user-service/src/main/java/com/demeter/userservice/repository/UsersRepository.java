package com.demeter.userservice.repository;


import com.demeter.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findBySub(String sub);
}
