package com.retronic.security.authorization.authenticator.hero;

import com.retronic.business.services.hero.impl.GameService;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.security.authorization.AuthorizationPermissionEvaluator;
import com.retronic.security.authorization.authenticator.core.Authenticator;
import com.retronic.security.enums.Permission;
import com.retronic.security.exceptions.BadObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.retronic.security.enums.Permission.GAME_READ;
import static com.retronic.security.enums.Permission.GAME_WRITE;

@Component
public class GameAuthenticator extends Authenticator {

    @Autowired
    private GameService gameService;

    @Override
    public List<Permission> getPermissions() {
        return Arrays.asList(GAME_READ, GAME_WRITE);
    }

    @Override
    public boolean hasPermission(User user, Integer id, Permission permission) {
        Game game = gameService.get(id);

        if (game == null) {
            throw new BadObjectException(String.format(AuthorizationPermissionEvaluator.NOT_FOUND, "gameId", id));
        }

        if (permission == Permission.GAME_READ) {
            return game.getUser() == null || game.getUser().getId().equals(user.getId()) || user.hasAdminAccess();
        } else if (permission == Permission.GAME_WRITE) {
            return game.getUser() == null || game.getUser().getId().equals(user.getId());
        }

        return false;
    }

    @Override
    public boolean hasPermission(User user, String string, Permission permission) {
        return false;
    }
}
