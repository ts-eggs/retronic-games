package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService extends GenericService<Game, Integer> {

    @Autowired
    private IGenericDao<Game, Integer> gameDao;

    @Override
    public void init() {
        this.genericDao = gameDao;
    }

    @Override
    public Integer create(Game game) {
        Integer maxId = (Integer) this.genericDao.getMaxId();
        game.setId(++maxId);
        return super.create(game);
    }
}