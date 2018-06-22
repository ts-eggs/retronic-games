package com.retronic.security.authorization.authenticator;

import com.retronic.persistence.entities.core.User;
import com.retronic.security.enums.Permission;

import java.util.List;

public interface IAuthenticator {

    List<Permission> getPermissions();

    boolean containsPermission(Permission permission);

    boolean hasPermission(User user, Integer id, Permission permission);

    boolean hasPermission(User user, String string, Permission permission);
}
