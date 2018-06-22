package com.retronic.remoting.dtos.hero;

import java.io.Serializable;

public class RaceDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251826L;

    public static final String URL_REPRESENTATION = "races";

    private Integer id;

    private String name;

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

    public SkillDto getSkillDto() {
        return skillDto;
    }

    public void setSkillDto(SkillDto skillDto) {
        this.skillDto = skillDto;
    }
}
