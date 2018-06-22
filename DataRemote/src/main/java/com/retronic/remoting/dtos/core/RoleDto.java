package com.retronic.remoting.dtos.core;

import java.io.Serializable;

public class RoleDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251819L;

    public static final String URL_REPRESENTATION = "roles";

    private Integer id;

    private String name;

    private Integer accessLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }
}
