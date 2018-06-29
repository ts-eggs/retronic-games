package com.retronic.security.authorization;

import com.retronic.persistence.entities.core.User;
import com.retronic.security.entities.SecurityUserDetails;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class ExtendedSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    public ExtendedSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    void setThis(Object target) {
        this.target = target;
    }

    @Override
    public Object getThis() {
        return target;
    }

    private User getAuthUser() {
        Object principal = authentication.getPrincipal();

        if (principal.getClass().equals(SecurityUserDetails.class)) {
            return ((SecurityUserDetails) principal).getUser();
        }

        return null;
    }

    public boolean hasSystemAccess() {
        User authUser = this.getAuthUser();
        return authUser != null && authUser.hasSystemAccess();
    }

    public boolean hasAdminAccess() {
        User authUser = this.getAuthUser();
        return authUser != null && authUser.hasAdminAccess();
    }

    public boolean hasAdvancedAccess() {
        User authUser = this.getAuthUser();
        return authUser != null && authUser.hasAdvancedAccess();
    }

    public boolean hasBaseAccess() {
        User authUser = this.getAuthUser();
        return authUser != null && authUser.hasBaseAccess();
    }

    public boolean isUser() {
        User authUser = this.getAuthUser();
        return authUser != null;
    }

    public boolean freeForAll() {
        return true;
    }
}
