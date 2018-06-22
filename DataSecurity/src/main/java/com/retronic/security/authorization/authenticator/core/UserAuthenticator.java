package com.retronic.security.authorization.authenticator.core;

import com.retronic.persistence.entities.core.User;
import com.retronic.security.enums.Permission;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.retronic.security.enums.Permission.*;

@Component
public class UserAuthenticator extends Authenticator {

    @Override
    public List<Permission> getPermissions() {
        return Arrays.asList(IS_USER, USER_READ, USER_WRITE);
    }

    @Override
    public boolean hasPermission(User user, Integer id, Permission permission) {
        if (permission == Permission.IS_USER) {
            return id.equals(user.getId());
        } else if (permission == Permission.USER_READ) {
            return user.hasAdminAccess();
        } else if (permission == Permission.USER_WRITE) {
            return user.hasSystemAccess();
        }

        return false;
    }

    @Override
    public boolean hasPermission(User user, String string, Permission permission) {
        return false;
    }
}
