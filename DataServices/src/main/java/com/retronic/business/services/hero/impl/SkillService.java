package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SkillService extends GenericService<Skill, Integer> {

    @Autowired
    private IGenericDao<Skill, Integer> skillDao;

    @Override
    public void init() {
        this.genericDao = skillDao;
    }
}