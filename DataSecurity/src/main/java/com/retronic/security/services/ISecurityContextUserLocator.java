package com.retronic.security.services;


import com.retronic.persistence.entities.core.User;


public interface ISecurityContextUserLocator {

    public User getSecurityContextUser();
}
