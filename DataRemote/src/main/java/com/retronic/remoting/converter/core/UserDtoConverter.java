package com.retronic.remoting.converter.core;

import com.retronic.persistence.entities.core.Country;
import com.retronic.persistence.entities.core.Role;
import com.retronic.persistence.entities.core.User;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.CountryDto;
import com.retronic.remoting.dtos.core.RoleDto;
import com.retronic.remoting.dtos.core.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements IDtoConverter<UserDto, User> {

    @Autowired
    private IDtoConverter<RoleDto, Role> roleDtoConverter;

    @Autowired
    private IDtoConverter<CountryDto, Country> countryDtoConverter;

    public UserDto convertToDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setAccess(entity.isAccess());
        dto.setSex(entity.getSex());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setStreet(entity.getStreet());
        dto.setCitycode(entity.getCitycode());
        dto.setCity(entity.getCity());
        dto.setLastlogin(entity.getLastlogin());
        dto.setCreated(entity.getCreated());

        if (entity.getRole() != null) {
            dto.setRoleDto(roleDtoConverter.convertToDto(entity.getRole()));
        }

        if (entity.getCountry() != null) {
            dto.setCountryDto(countryDtoConverter.convertToDto(entity.getCountry()));
        }

        return dto;
    }

    public User convertToEntity(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setAccess(dto.isAccess());
        entity.setSex(dto.getSex());
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setStreet(dto.getStreet());
        entity.setCitycode(dto.getCitycode());
        entity.setCity(dto.getCity());
        entity.setLastlogin(dto.getLastlogin());
        entity.setCreated(dto.getCreated());

        if (dto.getRoleDto() != null) {
            entity.setRole(roleDtoConverter.convertToEntity(dto.getRoleDto()));
        }

        if (dto.getCountryDto() != null) {
            entity.setCountry(countryDtoConverter.convertToEntity(dto.getCountryDto()));
        }

        return entity;
    }
}
