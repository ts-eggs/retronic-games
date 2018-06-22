package com.retronic.security.authorization.authenticator.core;

import com.retronic.persistence.entities.core.User;
import com.retronic.security.authorization.authenticator.IAuthenticator;
import com.retronic.security.enums.Permission;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Authenticator implements IAuthenticator {

    @Override
    public List<Permission> getPermissions() {
        return new ArrayList<>();
    }

    @Override
    public boolean containsPermission(Permission permission) {
        return getPermissions().contains(permission);
    }

    @Override
    public boolean hasPermission(User user, Integer id, Permission permission) {
        return false;
    }

    @Override
    public boolean hasPermission(User user, String string, Permission permission) {
        return false;
    }
}
