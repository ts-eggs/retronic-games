package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.business.services.hero.IGameService;
import com.retronic.business.utils.GenerationUtil;
import com.retronic.persistence.daos.hero.IGameDao;
import com.retronic.persistence.entities.hero.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService extends GenericService<Game, Integer> implements IGameService {

    @Autowired
    private IGameDao gameDao;

    @Override
    public void init() {
        this.genericDao = gameDao;
    }

    @Override
    public Game createGame(Game game) {
        Integer maxId = (Integer) this.genericDao.getMaxId();
        game.setId(++maxId);
        game.setSecret(maxId.toString() + GenerationUtil.generateToken());
        this.create(game);
        return game;
    }

    @Override
    public Game getBySecret(String secret) {
        return this.gameDao.readBySecret(secret);
    }
}