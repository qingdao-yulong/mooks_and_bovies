package com.qdu.repository;

import com.qdu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByUsername(String username);

    public User getUserByEmail(String email);

    public User getUserByUsernameAndPassword(String username, String password);

    public List<User> getUsersByAdmin(int superUser);

}
