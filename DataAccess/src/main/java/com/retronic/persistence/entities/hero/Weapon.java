package com.retronic.persistence.entities.hero;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "weapon", schema = "rgh")
public class Weapon implements Serializable {

    private static final long serialVersionUID = 2405172041950251817L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "level", nullable = true)
    private Integer level;

    @Column(name = "hands", nullable = true)
    private Integer hands;

    @Column(name = "costs", nullable = true)
    private Integer costs;

    @Column(name = "value", nullable = true)
    private Float value;

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

    public Integer getHands() {
        return hands;
    }

    public void setHands(Integer hands) {
        this.hands = hands;
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
