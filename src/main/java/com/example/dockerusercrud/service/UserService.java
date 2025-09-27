package com.example.dockerusercrud.service;

import com.example.dockerusercrud.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
