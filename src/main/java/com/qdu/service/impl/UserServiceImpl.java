package com.qdu.service.impl;

import com.qdu.repository.UserRepository;
import com.qdu.entity.User;
import com.qdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository ud;

    @Autowired
    public UserServiceImpl(UserRepository ud) {
        this.ud = ud;
    }


    @Override
    public User getUserById(int id) {
        return ud.findById(id).get();
    }

    @Override
    public User getUserByUsername(String username) {
        return ud.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        return ud.getUserByEmail(email);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return ud.getUserByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getUsersBySuperUser(int superUser) {
        return ud.getUsersByAdmin(superUser);
    }

    @Override
    public User createUser(User user) {
        user.setAdmin(0);
        return ud.save(user);
    }

    @Override
    public User createAdmin(User user) {
        user.setAdmin(1);
        return ud.save(user);
    }

    @Override
    public User updateUser(User user) {
        return ud.save(user);
    }

    @Override
    public void deleteUser(int user) {
        ud.deleteById(user);
    }
}
