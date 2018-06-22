package com.retronic.persistence.entities.hero;

import com.retronic.persistence.entities.core.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "character", schema = "rgh")
public class Character implements Serializable {

    private static final long serialVersionUID = 2405172041950251811L;

    @Id
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "fixed", nullable = true)
    private boolean fixed;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "strength", nullable = false)
    private Integer strength;

    @Column(name = "vitality", nullable = false)
    private Integer vitality;

    @Column(name = "dexterity", nullable = false)
    private Integer dexterity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkUserId")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkRaceId")
    private Race race;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkClassId")
    private Class heroClass;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.characterArmorRefs", joinColumns = {@JoinColumn(name = "fkCharacterId")}, inverseJoinColumns = {@JoinColumn(name = "fkArmorId")})
    private List<Armor> armor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.characterWeaponRefs", joinColumns = {@JoinColumn(name = "fkCharacterId")}, inverseJoinColumns = {@JoinColumn(name = "fkWeaponId")})
    private List<Weapon> weapons;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.characterItemRefs", joinColumns = {@JoinColumn(name = "fkCharacterId")}, inverseJoinColumns = {@JoinColumn(name = "fkItemId")})
    private List<Item> items;

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

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getVitality() {
        return vitality;
    }

    public void setVitality(Integer vitality) {
        this.vitality = vitality;
    }

    public Integer getDexterity() {
        return dexterity;
    }

    public void setDexterity(Integer dexterity) {
        this.dexterity = dexterity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Class getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(Class heroClass) {
        this.heroClass = heroClass;
    }

    public List<Armor> getArmor() {
        return armor;
    }

    public void setArmor(List<Armor> armor) {
        this.armor = armor;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
