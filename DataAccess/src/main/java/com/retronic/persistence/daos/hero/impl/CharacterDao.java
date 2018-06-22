package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Character;
import org.springframework.stereotype.Repository;

@Repository("characterDao")
public class CharacterDao extends GenericDao<Character, Integer> {

    @Override
    public void init() {
        this.entityClassType = Character.class;
    }
}