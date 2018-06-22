package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Skill;
import com.retronic.persistence.entities.hero.Weapon;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.dtos.hero.WeaponDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WeaponDtoConverter implements IDtoConverter<WeaponDto, Weapon> {

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    public WeaponDto convertToDto(Weapon entity) {
        WeaponDto dto = new WeaponDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setHands(entity.getHands());
        dto.setCosts(entity.getCosts());
        dto.setValue(entity.getValue());

        if (entity.getSkill() != null) {
            dto.setSkillDto(skillDtoConverter.convertToDto(entity.getSkill()));
        }

        return dto;
    }

    public Weapon convertToEntity(WeaponDto dto) {
        Weapon entity = new Weapon();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setHands(dto.getHands());
        entity.setCosts(dto.getCosts());
        entity.setValue(dto.getValue());

        if (dto.getSkillDto() != null) {
            entity.setSkill(skillDtoConverter.convertToEntity(dto.getSkillDto()));
        }

        return entity;
    }
}
