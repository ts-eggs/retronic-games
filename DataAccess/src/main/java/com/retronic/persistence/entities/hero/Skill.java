package com.retronic.persistence.entities.hero;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "skill", schema = "rgh")
public class Skill implements Serializable {

    private static final long serialVersionUID = 2405172041950251816L;

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

    @Column(name = "action", nullable = true)
    private Integer action;

    @Column(name = "value", nullable = true)
    private Float value;

    @Column(name = "multi", nullable = true)
    private boolean multi;

    @Column(name = "permanent", nullable = true)
    private boolean permanent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.classSkillRefs", joinColumns = {@JoinColumn(name = "fkSkillId")}, inverseJoinColumns = {@JoinColumn(name = "fkClassId")})
    private List<Class> classes;

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

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public List<Class> getClasses() {
        return classes;
    }

    public void setClasses(List<Class> classes) {
        this.classes = classes;
    }
}
