package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Armor;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.ArmorDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArmorDtoConverter implements IDtoConverter<ArmorDto, Armor> {

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    public ArmorDto convertToDto(Armor entity) {
        ArmorDto dto = new ArmorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLevel(entity.getLevel());
        dto.setHands(entity.getHands());
        dto.setCosts(entity.getCosts());
        dto.setValue(entity.getValue());

        if(entity.getSkill() != null) {
            dto.setSkillDto(skillDtoConverter.convertToDto(entity.getSkill()));
        }

        return dto;
    }

    public Armor convertToEntity(ArmorDto dto) {
        Armor entity = new Armor();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLevel(dto.getLevel());
        entity.setHands(dto.getHands());
        entity.setCosts(dto.getCosts());
        entity.setValue(dto.getValue());

        if(dto.getSkillDto() != null) {
            entity.setSkill(skillDtoConverter.convertToEntity(dto.getSkillDto()));
        }

        return entity;
    }
}
