package com.retronic.persistence.daos.hero.impl;

import com.retronic.persistence.daos.core.impl.GenericDao;
import com.retronic.persistence.daos.hero.IGameDao;
import com.retronic.persistence.entities.hero.Game;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("gameDao")
public class GameDao extends GenericDao<Game, Integer> implements IGameDao {

    @Override
    public void init() {
        this.entityClassType = Game.class;
    }

    @Override
    public Game readBySecret(String secret) {
        return this.findByCriterion(Restrictions.eq("secret", secret));
    }
}