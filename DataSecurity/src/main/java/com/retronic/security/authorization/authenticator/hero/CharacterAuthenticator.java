package com.retronic.security.authorization.authenticator.hero;

import com.retronic.business.services.hero.impl.CharacterService;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.security.authorization.AuthorizationPermissionEvaluator;
import com.retronic.security.authorization.authenticator.core.Authenticator;
import com.retronic.security.enums.Permission;
import com.retronic.security.exceptions.BadObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.retronic.security.enums.Permission.CHARACTER_READ;
import static com.retronic.security.enums.Permission.CHARACTER_WRITE;

@Component
public class CharacterAuthenticator extends Authenticator {

    @Autowired
    private CharacterService characterService;

    @Override
    public List<Permission> getPermissions() {
        return Arrays.asList(CHARACTER_READ, CHARACTER_WRITE);
    }

    @Override
    public boolean hasPermission(User user, Integer id, Permission permission) {
        Character character = characterService.get(id);

        if (character == null) {
            throw new BadObjectException(String.format(AuthorizationPermissionEvaluator.NOT_FOUND, "characterId", id));
        }

        if (permission == Permission.CHARACTER_READ) {
            return character.getUser() == null || character.getUser().getId().equals(user.getId()) || user.hasAdminAccess();
        } else if (permission == Permission.CHARACTER_WRITE) {
            return character.getUser() == null || character.getUser().getId().equals(user.getId());
        }

        return false;
    }

    @Override
    public boolean hasPermission(User user, String string, Permission permission) {
        return false;
    }
}
