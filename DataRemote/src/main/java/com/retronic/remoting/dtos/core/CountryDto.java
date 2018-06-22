package com.retronic.remoting.dtos.core;

import java.io.Serializable;

public class CountryDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251818L;

    public static final String URL_REPRESENTATION = "countries";

    private Integer id;

    private String name;

    private String domain;

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
