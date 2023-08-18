package com.demeter.userservice.repository;


import com.demeter.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
}
