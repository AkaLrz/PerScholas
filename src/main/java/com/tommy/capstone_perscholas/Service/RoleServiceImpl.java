package com.tommy.capstone_perscholas.Service;


import com.tommy.capstone_perscholas.Dao.RoleDao;
import com.tommy.capstone_perscholas.model.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    @Transactional
    public void saveRole(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public Role findRoleByRoleName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>)roleDao.findAll();
    }

    @Override
    public List<Role> getRolesByUser(long id) {
        return roleDao.findRoleByUser(id);
    }
}
