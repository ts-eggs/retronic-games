package com.retronic.security.entities;

import com.retronic.persistence.entities.core.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUserDetails implements UserDetails, Serializable {

    private User user;

    public SecurityUserDetails(User user) {
        this.user = user;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        SecurityUserRoles authority = new SecurityUserRoles();
        authority.setAuthority("ROLE_" + this.user.getRole().getName().toUpperCase());
        authorities.add(authority);
        return authorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getLogin();
    }

    public boolean isAccountNonExpired() {
        return user.isAccess();
    }

    public boolean isAccountNonLocked() {
        return user.isAccess();
    }

    public boolean isCredentialsNonExpired() {
        return user.isAccess();
    }

    public boolean isEnabled() {
        return user.isAccess();
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
