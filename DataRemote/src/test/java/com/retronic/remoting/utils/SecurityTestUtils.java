package com.retronic.remoting.utils;

import com.retronic.persistence.daos.core.IUserDao;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.entities.core.User;
import com.retronic.security.entities.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Service;

@Service
public class SecurityTestUtils {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private ProviderManager authenticationManager;

    public void setUpAuthentication(Integer userId) {
        User user = userDao.read(userId);
        Role role = user.getRole();
        String[] roleNames = new String[1];
        roleNames[0] = "ROLE_" + role.getName().toUpperCase();
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(new SecurityUserDetails(user), user.getPassword(), roleNames);
        testingAuthenticationToken.setAuthenticated(true);
        authenticationManager.getProviders().add(new TestingAuthenticationProvider());
        SecurityContextImpl securityImpl = new SecurityContextImpl();
        securityImpl.setAuthentication(testingAuthenticationToken);
        SecurityContextHolder.setContext(securityImpl);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        SecurityUserDetails userDetails = (SecurityUserDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
