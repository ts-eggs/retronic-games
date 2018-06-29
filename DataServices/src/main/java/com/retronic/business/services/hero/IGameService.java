package com.retronic.business.services.hero;

import com.retronic.business.services.IGenericService;
import com.retronic.persistence.entities.hero.Game;

public interface IGameService extends IGenericService<Game, Integer> {

    Game createGame(Game game);

    Game getBySecret(String secret);
}
