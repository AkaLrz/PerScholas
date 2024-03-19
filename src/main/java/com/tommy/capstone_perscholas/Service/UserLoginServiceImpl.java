package com.tommy.capstone_perscholas.Service;

import com.tommy.capstone_perscholas.Dao.UserDao;
import com.tommy.capstone_perscholas.Dto.UserDTO;
import com.tommy.capstone_perscholas.model.Role;
import com.tommy.capstone_perscholas.model.User;
import com.tommy.capstone_perscholas.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        log.debug(userName);
        if(user == null){
            log.warn("User not found with username: " + userName);
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }
        return new UserPrincipal(user,roleService.getRolesByUser(user.getId()));
    }

    @Transactional
    public void creat(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDTO, User.class);

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_USER")));

        userDao.save(user);
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public User findUserByEmail(String email)
    {
        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findByUserName(name);
    }

    @Override
    public boolean existsByUsername(String userName) {
        return userDao.existsByUserName(userName);
    }


}
