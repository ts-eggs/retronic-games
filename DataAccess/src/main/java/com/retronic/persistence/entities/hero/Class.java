package com.retronic.persistence.entities.hero;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "class", schema = "rgh")
public class Class implements Serializable {

    private static final long serialVersionUID = 2405172041950251812L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.classSkillRefs", joinColumns = {@JoinColumn(name = "fkClassId")}, inverseJoinColumns = {@JoinColumn(name = "fkSkillId")})
    private List<Skill> skills;

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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
