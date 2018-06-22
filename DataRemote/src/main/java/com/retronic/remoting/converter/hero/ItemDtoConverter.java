package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Item;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.ItemDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoConverter implements IDtoConverter<ItemDto, Item> {

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    public ItemDto convertToDto(Item entity) {
        ItemDto dto = new ItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLevel(entity.getLevel());
        dto.setCosts(entity.getCosts());
        dto.setValue(entity.getValue());

        if (entity.getSkill() != null) {
            dto.setSkillDto(skillDtoConverter.convertToDto(entity.getSkill()));
        }

        return dto;
    }

    public Item convertToEntity(ItemDto dto) {
        Item entity = new Item();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setLevel(dto.getLevel());
        entity.setCosts(dto.getCosts());
        entity.setValue(dto.getValue());

        if (dto.getSkillDto() != null) {
            entity.setSkill(skillDtoConverter.convertToEntity(dto.getSkillDto()));
        }

        return entity;
    }
}
