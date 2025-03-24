package com.training.service;

import com.training.entity.User;

import java.util.Optional;

public interface IUserService {

    Optional<User> findByUsername(String username);
}
