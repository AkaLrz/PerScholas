package com.tommy.capstone_perscholas.Service;

import com.tommy.capstone_perscholas.Dto.UserDTO;
import com.tommy.capstone_perscholas.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserLoginService extends UserDetailsService {
    public UserDetails loadUserByUsername(String userName);
    public void creat(UserDTO userDTO);
    public User findUserByEmail(String email);
    public User findUserByName(String name);
    boolean existsByUsername(String userName);
}
