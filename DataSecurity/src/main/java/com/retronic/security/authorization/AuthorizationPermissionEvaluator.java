package com.retronic.security.authorization;

import com.retronic.security.authorization.authenticator.IAuthenticator;
import com.retronic.security.entities.SecurityUserDetails;
import com.retronic.security.enums.Permission;
import com.retronic.security.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service("permissionEvaluator")
public class AuthorizationPermissionEvaluator implements PermissionEvaluator {

    public static final String NOT_FOUND = "%s %s not found.";

    @Autowired
    List<IAuthenticator> authenticators;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (targetDomainObject == null) {
            throw new NotFoundException();
        }

        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();

        for (IAuthenticator authenticator : authenticators) {
            if (authenticator.containsPermission((Permission) permission)) {
                return authenticator.hasPermission(securityUserDetails.getUser(), (Integer) targetDomainObject, (Permission) permission);
            }
        }

        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return true;
    }
}