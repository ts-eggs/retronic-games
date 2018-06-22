package com.retronic.remoting.converter.core;

import com.retronic.persistence.entities.core.Country;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.CountryDto;
import org.springframework.stereotype.Component;

@Component
public class CountryDtoConverter implements IDtoConverter<CountryDto, Country> {

    public CountryDto convertToDto(Country entity) {
        CountryDto dto = new CountryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDomain(entity.getDomain());
        return dto;
    }

    public Country convertToEntity(CountryDto dto) {
        Country entity = new Country();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDomain(dto.getDomain());
        return entity;
    }
}
