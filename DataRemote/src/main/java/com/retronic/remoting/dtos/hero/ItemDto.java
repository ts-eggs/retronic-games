package com.retronic.remoting.dtos.hero;

import java.io.Serializable;

public class ItemDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251825L;

    public static final String URL_REPRESENTATION = "items";

    private Integer id;

    private String name;

    private String description;

    private Integer level;

    private Integer costs;

    private Float value;

    private SkillDto skillDto;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getCosts() {
        return costs;
    }

    public void setCosts(Integer costs) {
        this.costs = costs;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public SkillDto getSkillDto() {
        return skillDto;
    }

    public void setSkillDto(SkillDto skillDto) {
        this.skillDto = skillDto;
    }
}
