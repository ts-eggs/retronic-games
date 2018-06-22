package com.retronic.remoting.converter.core;

import com.retronic.persistence.entities.core.Role;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.RoleDto;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter implements IDtoConverter<RoleDto, Role> {

    public RoleDto convertToDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAccessLevel(entity.getAccessLevel());
        return dto;
    }

    public Role convertToEntity(RoleDto dto) {
        Role entity = new Role();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAccessLevel(dto.getAccessLevel());
        return entity;
    }
}
