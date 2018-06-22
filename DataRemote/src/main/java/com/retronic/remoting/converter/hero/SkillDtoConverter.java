package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.SkillDto;
import org.springframework.stereotype.Component;

@Component
public class SkillDtoConverter implements IDtoConverter<SkillDto, Skill> {

    public SkillDto convertToDto(Skill entity) {
        SkillDto dto = new SkillDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setAction(entity.getAction());
        dto.setLevel(entity.getLevel());
        dto.setPermanent(entity.isPermanent());
        dto.setMulti(entity.isMulti());
        dto.setValue(entity.getValue());
        return dto;
    }

    public Skill convertToEntity(SkillDto dto) {
        Skill entity = new Skill();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setAction(dto.getAction());
        entity.setLevel(dto.getLevel());
        entity.setPermanent(dto.isPermanent());
        entity.setMulti(dto.isMulti());
        entity.setValue(dto.getValue());
        return entity;
    }
}
