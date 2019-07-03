package com.ascending.project.repository;

import com.ascending.project.domain.User;

import java.util.List;

public interface UserDao {

    User save (User user);

    List<User> findAll();

    User findByIdEager(Long id);

    User findById(Long id);
}
