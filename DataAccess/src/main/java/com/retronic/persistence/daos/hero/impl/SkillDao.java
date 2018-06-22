package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Skill;
import org.springframework.stereotype.Repository;

@Repository("skillDao")
public class SkillDao extends GenericDao<Skill, Integer> {

    @Override
    public void init() {
        this.entityClassType = Skill.class;
    }
}