package com.retronic.remoting.converter.hero;

import com.retronic.business.services.core.IUserService;
import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Game;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.dtos.hero.CharacterDto;
import com.retronic.remoting.dtos.hero.GameDto;
import com.retronic.remoting.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameDtoConverter implements IDtoConverter<GameDto, Game> {

    @Autowired
    private IUserService userService;

    @Autowired
    private IDtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    private IDtoConverter<CharacterDto, Character> characterDtoConverter;

    public GameDto convertToDto(Game entity) {
        GameDto dto = new GameDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDifficult(entity.getDifficult());
        dto.setSecret(entity.getSecret());

        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }

        dto.setCharacterReference(GameDto.URL_REPRESENTATION + "/" + entity.getId() + "/" + CharacterDto.URL_REPRESENTATION);

        if (entity.getCharacters() != null && !entity.getCharacters().isEmpty()) {
            List<CharacterDto> characterDtos = entity.getCharacters().stream().map(character -> characterDtoConverter.convertToDto(character)).collect(Collectors.toList());
            dto.setCharacterDtos(characterDtos);
        }

        return dto;
    }

    public Game convertToEntity(GameDto dto) {
        Game entity = new Game();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDifficult(dto.getDifficult());
        entity.setSecret(dto.getSecret());

        if (dto.getUserId() != null) {
            entity.setUser(userService.get(dto.getUserId()));
        }

        if (dto.getCharacterDtos() != null && !dto.getCharacterDtos().isEmpty()) {
            entity.setCharacters(ConverterUtils.convertToEntities(characterDtoConverter, dto.getCharacterDtos()));
        }

        return entity;
    }
}
