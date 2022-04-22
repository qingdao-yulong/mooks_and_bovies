package com.qdu.service;

import com.qdu.entity.User;

import java.util.List;

public interface UserService {

    public User getUserById(int id);

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public User getUserByUsernameAndPassword(String username, String password);

    public List<User> getUsersBySuperUser(int superUser);

    public User createUser(User user);

    public User createAdmin(User user);

    public User updateUser(User user);

    public void deleteUser(int user);

}
