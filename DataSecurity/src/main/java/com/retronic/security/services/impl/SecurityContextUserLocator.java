package com.retronic.security.services.impl;

import com.retronic.persistence.entities.core.User;
import com.retronic.security.entities.SecurityUserDetails;
import com.retronic.security.services.ISecurityContextUserLocator;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextUserLocator implements ISecurityContextUserLocator {

    @Override
    public User getSecurityContextUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal.getClass().equals(SecurityUserDetails.class)) {
            return ((SecurityUserDetails) principal).getUser();
        }

        return null;
    }
}
