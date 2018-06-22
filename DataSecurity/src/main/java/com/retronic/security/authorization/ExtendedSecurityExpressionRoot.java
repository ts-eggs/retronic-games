package com.retronic.security.authorization;

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

    public boolean hasSystemAccess() {
        SecurityUserDetails securityUser = (SecurityUserDetails) authentication.getPrincipal();
        return securityUser.getUser().hasSystemAccess();
    }

    public boolean hasAdminAccess() {
        SecurityUserDetails securityUser = (SecurityUserDetails) authentication.getPrincipal();
        return securityUser.getUser().hasAdminAccess();
    }

    public boolean hasAdvancedAccess() {
        SecurityUserDetails securityUser = (SecurityUserDetails) authentication.getPrincipal();
        return securityUser.getUser().hasAdvancedAccess();
    }

    public boolean hasBaseAccess() {
        SecurityUserDetails securityUser = (SecurityUserDetails) authentication.getPrincipal();
        return securityUser.getUser().hasBaseAccess();
    }

    public boolean freeForAll() {
        return true;
    }
}
