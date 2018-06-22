package com.retronic.persistence.daos.core.impl;

import com.retronic.persistence.entities.core.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDao extends GenericDao<Role, Integer> {

    @Override
    public void init() {
        this.entityClassType = Role.class;
    }
}
