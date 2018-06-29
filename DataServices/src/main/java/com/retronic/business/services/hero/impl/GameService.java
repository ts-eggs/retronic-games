package com.retronic.business.services.hero.impl;

import com.retronic.business.services.core.impl.GenericService;
import com.retronic.business.services.hero.IGameService;
import com.retronic.persistence.daos.hero.IGameDao;
import com.retronic.persistence.entities.hero.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

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
        Integer randomNum = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        Long timeRandom = Calendar.getInstance().getTimeInMillis() / randomNum;
        game.setSecret(maxId.toString() + timeRandom.toString() + randomNum.toString());
        this.create(game);
        return game;
    }

    @Override
    public Game getBySecret(String secret) {
        return this.gameDao.readBySecret(secret);
    }
}