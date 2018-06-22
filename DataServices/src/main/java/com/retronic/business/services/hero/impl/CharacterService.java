package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterService extends GenericService<Character, Integer> {

    @Autowired
    private IGenericDao<Character, Integer> characterDao;

    @Override
    public void init() {
        this.genericDao = characterDao;
    }

    @Override
    public Integer create(Character character) {
        Integer maxId = (Integer) this.genericDao.getMaxId();
        character.setId(++maxId);
        return super.create(character);
    }
}