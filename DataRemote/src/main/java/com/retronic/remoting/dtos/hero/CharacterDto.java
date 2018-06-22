package com.retronic.remoting.dtos.hero;

import com.retronic.remoting.dtos.core.UserDto;

import java.io.Serializable;
import java.util.List;

public class CharacterDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251822L;

    public static final String URL_REPRESENTATION = "characters";

    private Integer id;

    private String name;

    private boolean fixed;

    private Integer level;

    private Integer strength;

    private Integer vitality;

    private Integer dexterity;

    private UserDto userDto;

    private RaceDto raceDto;

    private ClassDto classDto;

    private List<ArmorDto> armorDtos;

    private String armorReference;

    private List<WeaponDto> weaponDtos;

    private String weaponReference;

    private List<ItemDto> itemDtos;

    private String itemReference;

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

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public RaceDto getRaceDto() {
        return raceDto;
    }

    public void setRaceDto(RaceDto raceDto) {
        this.raceDto = raceDto;
    }

    public ClassDto getClassDto() {
        return classDto;
    }

    public void setClassDto(ClassDto classDto) {
        this.classDto = classDto;
    }

    public List<ArmorDto> getArmorDtos() {
        return armorDtos;
    }

    public void setArmorDtos(List<ArmorDto> armorDtos) {
        this.armorDtos = armorDtos;
    }

    public String getArmorReference() {
        return armorReference;
    }

    public void setArmorReference(String armorReference) {
        this.armorReference = armorReference;
    }

    public List<WeaponDto> getWeaponDtos() {
        return weaponDtos;
    }

    public void setWeaponDtos(List<WeaponDto> weaponDtos) {
        this.weaponDtos = weaponDtos;
    }

    public String getWeaponReference() {
        return weaponReference;
    }

    public void setWeaponReference(String weaponReference) {
        this.weaponReference = weaponReference;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }
}
