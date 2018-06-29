package com.retronic.persistence.entities.core;

import com.retronic.persistence.entities.hero.Game;
import com.retronic.persistence.enums.core.RoleType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "[user]", schema = "rgc")
public class User implements Serializable {

    private static final long serialVersionUID = 2405172041950251809L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkRoleId")
    private Role role;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkCountryId")
    private Country country;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "access", nullable = false)
    private boolean access;

    @Column(name = "loginTrials", nullable = false)
    private Integer loginTrials;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "street")
    private String street;

    @Column(name = "citycode")
    private String citycode;

    @Column(name = "city")
    private String city;

    @Column(name = "lastlogin")
    private Calendar lastlogin;

    @Column(name = "created")
    private Calendar created;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "rgh.game", joinColumns = {@JoinColumn(name = "fkUserId", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
    private List<Game> games;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public boolean hasRole(String role) {
        return this.role.getName().equals(role);
    }

    public boolean hasAccess(Integer access) {
        return this.role.getAccessLevel() >= access;
    }

    public boolean hasSystemAccess() {
        return this.hasAccess(RoleType.SYSTEM.getAccess());
    }

    public boolean hasAdminAccess() {
        return this.hasAccess(RoleType.ADMIN.getAccess());
    }

    public boolean hasOperatorAccess() {
        return this.hasAccess(RoleType.OPERATOR.getAccess());
    }

    public boolean hasAdvancedAccess() {
        return this.hasAccess(RoleType.ADVANCED.getAccess());
    }

    public boolean hasBaseAccess() {
        return this.hasAccess(RoleType.BASE.getAccess());
    }

    public void resetTrialCount() {
        this.loginTrials = 0;
    }

    public void pushTrialCount() {
        this.loginTrials++;
    }
}
