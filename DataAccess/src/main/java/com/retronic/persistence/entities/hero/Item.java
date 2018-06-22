package com.retronic.persistence.entities.hero;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "item", schema = "rgh")
public class Item implements Serializable {

    private static final long serialVersionUID = 2405172041950251814L;

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

    @Column(name = "costs", nullable = true)
    private Integer costs;

    @Column(name = "value", nullable = true)
    private Float value;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkSkillId")
    private Skill skill;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.characterItemRefs", joinColumns = {@JoinColumn(name = "fkItemId")}, inverseJoinColumns = {@JoinColumn(name = "fkCharacterId")})
    private List<Character> characters;

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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
