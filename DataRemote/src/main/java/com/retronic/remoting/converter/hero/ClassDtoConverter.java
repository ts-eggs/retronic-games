package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.hero.Class;
import com.retronic.persistence.entities.hero.Skill;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.hero.ClassDto;
import com.retronic.remoting.dtos.hero.SkillDto;
import com.retronic.remoting.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClassDtoConverter implements IDtoConverter<ClassDto, Class> {

    @Autowired
    private IDtoConverter<SkillDto, Skill> skillDtoConverter;

    public ClassDto convertToDto(Class entity) {
        ClassDto dto = new ClassDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSkillReferences(ClassDto.URL_REPRESENTATION + "/" + entity.getId() + "/" + SkillDto.URL_REPRESENTATION);

        if (entity.getSkills() != null && !entity.getSkills().isEmpty()) {
            List<SkillDto> skillDtos = entity.getSkills().stream().map(skill -> skillDtoConverter.convertToDto(skill)).collect(Collectors.toList());
            dto.setSkillDtos(skillDtos);
        }

        return dto;
    }

    public Class convertToEntity(ClassDto dto) {
        Class entity = new Class();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        if (dto.getSkillDtos() != null && !dto.getSkillDtos().isEmpty()) {
            entity.setSkills(ConverterUtils.convertToEntities(skillDtoConverter, dto.getSkillDtos()));
        }

        return entity;
    }
}
