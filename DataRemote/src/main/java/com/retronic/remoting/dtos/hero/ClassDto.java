package com.retronic.remoting.dtos.hero;

import java.io.Serializable;
import java.util.List;

public class ClassDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251823L;

    public static final String URL_REPRESENTATION = "classes";

    private Integer id;

    private String name;

    private List<SkillDto> skillDtos;

    private String skillReferences;

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

    public List<SkillDto> getSkillDtos() {
        return skillDtos;
    }

    public void setSkillDtos(List<SkillDto> skillDtos) {
        this.skillDtos = skillDtos;
    }

    public String getSkillReferences() {
        return skillReferences;
    }

    public void setSkillReferences(String skillReference) {
        this.skillReferences = skillReference;
    }
}
