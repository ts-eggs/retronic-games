package com.retronic.persistence.daos.hero;

import com.retronic.persistence.daos.core.IGenericDao;
import com.retronic.persistence.entities.hero.Game;

public interface IGameDao extends IGenericDao<Game, Integer> {

    Game readBySecret(String secret);
}
