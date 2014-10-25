package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

import java.util.List;
/**
 * Created by alesya on 25.10.14.
 */
public interface UserService {

    public void addUser(User user);

    public User getUserByLogin(String login);

    public List<User> getUsers();

    public void removeUser(Long userId);

    public User getUserById(long userId);

    public void updateUser(User user);
}
