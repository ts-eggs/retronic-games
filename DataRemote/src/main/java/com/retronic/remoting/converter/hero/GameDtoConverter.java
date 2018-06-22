package com.retronic.remoting.converter.hero;

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
    private IDtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    private IDtoConverter<CharacterDto, Character> characterDtoConverter;

    public GameDto convertToDto(Game entity) {
        GameDto dto = new GameDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDifficult(entity.getDifficult());

        if(entity.getUser() != null) {
            dto.setUserDto(userDtoConverter.convertToDto(entity.getUser()));
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

        if(dto.getUserDto() != null) {
            entity.setUser(userDtoConverter.convertToEntity(dto.getUserDto()));
        }

        if (dto.getCharacterDtos() != null && !dto.getCharacterDtos().isEmpty()) {
            entity.setCharacters(ConverterUtils.convertToEntities(characterDtoConverter, dto.getCharacterDtos()));
        }

        return entity;
    }
}
