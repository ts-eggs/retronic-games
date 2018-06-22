package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.entities.hero.Game;
import org.springframework.stereotype.Repository;

@Repository("gameDao")
public class GameDao extends GenericDao<Game, Integer> {

    @Override
    public void init() {
        this.entityClassType = Game.class;
    }
}