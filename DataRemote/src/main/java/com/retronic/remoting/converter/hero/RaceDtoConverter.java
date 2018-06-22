package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Race;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.RaceDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceDtoConverter implements IDtoConverter<RaceDto, Race> {

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    public RaceDto convertToDto(Race entity) {
        RaceDto dto = new RaceDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        if (entity.getSkill() != null) {
            dto.setSkillDto(skillDtoConverter.convertToDto(entity.getSkill()));
        }

        return dto;
    }

    public Race convertToEntity(RaceDto dto) {
        Race entity = new Race();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getSkillDto() != null) {
            entity.setSkill(skillDtoConverter.convertToEntity(dto.getSkillDto()));
        }

        return entity;
    }
}
