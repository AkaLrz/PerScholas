package com.tommy.capstone_perscholas.Dao;

import com.tommy.capstone_perscholas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
    public User findUserByEmail(String email);
}
