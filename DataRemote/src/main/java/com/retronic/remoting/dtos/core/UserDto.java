package com.retronic.remoting.dtos.core;

import java.io.Serializable;
import java.util.Calendar;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251820L;

    public static final String URL_REPRESENTATION = "users";

    private Integer id;

    private String login;

    private String password;

    private boolean access;

    private Integer loginTrials;

    private Integer sex;

    private String firstname;

    private String lastname;

    private String street;

    private String citycode;

    private String city;

    private Calendar lastlogin;

    private Calendar created;

    private RoleDto roleDto;

    private CountryDto countryDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Integer getLoginTrials() {
        return loginTrials;
    }

    public void setLoginTrials(Integer loginTrials) {
        this.loginTrials = loginTrials;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Calendar getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Calendar lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public RoleDto getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(RoleDto roleDto) {
        this.roleDto = roleDto;
    }

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }
}
