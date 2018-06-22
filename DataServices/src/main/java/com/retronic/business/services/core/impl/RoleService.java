package com.retronic.business.services.core.impl;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.core.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends GenericService<Role, Integer> {

    @Autowired
    private IGenericDao<Role, Integer> roleDao;

    @Override
    public void init() {
        this.genericDao = roleDao;
    }
}
