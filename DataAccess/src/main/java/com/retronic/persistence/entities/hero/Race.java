package com.retronic.persistence.entities.hero;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "race", schema = "rgh")
public class Race implements Serializable {

    private static final long serialVersionUID = 2405172041950251815L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkSkillId")
    private Skill skill;

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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
