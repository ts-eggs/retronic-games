package com.retronic.remoting.converter.hero;

import com.retronic.persistence.entities.core.User;
import com.retronic.persistence.entities.hero.*;
import com.retronic.persistence.entities.hero.Character;
import com.retronic.persistence.entities.hero.Class;
import com.retronic.remoting.converter.IDtoConverter;
import com.retronic.remoting.dtos.core.UserDto;
import com.retronic.remoting.dtos.hero.*;
import com.retronic.remoting.utils.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CharacterDtoConverter implements IDtoConverter<CharacterDto, Character> {

    @Autowired
    private IDtoConverter<UserDto, User> userDtoConverter;

    @Autowired
    private IDtoConverter<RaceDto, Race> raceDtoConverter;

    @Autowired
    private IDtoConverter<ClassDto, Class> classDtoConverter;

    @Autowired
    private IDtoConverter<ArmorDto, Armor> armorDtoConverter;

    @Autowired
    private IDtoConverter<WeaponDto, Weapon> weaponDtoConverter;

    @Autowired
    private IDtoConverter<ItemDto, Item> itemDtoConverter;

    public CharacterDto convertToDto(Character entity) {
        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setFixed(entity.isFixed());
        dto.setStrength(entity.getStrength());
        dto.setVitality(entity.getVitality());
        dto.setDexterity(entity.getDexterity());

        if(entity.getUser() != null) {
            dto.setUserDto(userDtoConverter.convertToDto(entity.getUser()));
        }

        dto.setRaceDto(raceDtoConverter.convertToDto(entity.getRace()));
        dto.setClassDto(classDtoConverter.convertToDto(entity.getHeroClass()));
        dto.setArmorReference(CharacterDto.URL_REPRESENTATION + "/" + entity.getId() + "/" + ArmorDto.URL_REPRESENTATION);
        dto.setWeaponReference(CharacterDto.URL_REPRESENTATION + "/" + entity.getId() + "/" + WeaponDto.URL_REPRESENTATION);
        dto.setItemReference(CharacterDto.URL_REPRESENTATION + "/" + entity.getId() + "/" + ItemDto.URL_REPRESENTATION);

        if (entity.getArmor() != null && !entity.getArmor().isEmpty()) {
            List<ArmorDto> armorDtos = entity.getArmor().stream().map(armor -> armorDtoConverter.convertToDto(armor)).collect(Collectors.toList());
            dto.setArmorDtos(armorDtos);
        }

        if (entity.getWeapons() != null && !entity.getWeapons().isEmpty()) {
            List<WeaponDto> weaponDtos = entity.getWeapons().stream().map(weapon -> weaponDtoConverter.convertToDto(weapon)).collect(Collectors.toList());
            dto.setWeaponDtos(weaponDtos);
        }

        if (entity.getItems() != null && !entity.getItems().isEmpty()) {
            List<ItemDto> itemDtos = entity.getItems().stream().map(item -> itemDtoConverter.convertToDto(item)).collect(Collectors.toList());
            dto.setItemDtos(itemDtos);
        }

        return dto;
    }

    public Character convertToEntity(CharacterDto dto) {
        Character entity = new Character();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setFixed(dto.isFixed());
        entity.setStrength(dto.getStrength());
        entity.setVitality(dto.getVitality());
        entity.setDexterity(dto.getDexterity());

        if(dto.getUserDto() != null) {
            entity.setUser(userDtoConverter.convertToEntity(dto.getUserDto()));
        }

        entity.setRace(raceDtoConverter.convertToEntity(dto.getRaceDto()));
        entity.setHeroClass(classDtoConverter.convertToEntity(dto.getClassDto()));

        if (dto.getArmorDtos() != null && !dto.getArmorDtos().isEmpty()) {
            entity.setArmor(ConverterUtils.convertToEntities(armorDtoConverter, dto.getArmorDtos()));
        }

        if (dto.getWeaponDtos() != null && !dto.getWeaponDtos().isEmpty()) {
            entity.setWeapons(ConverterUtils.convertToEntities(weaponDtoConverter, dto.getWeaponDtos()));
        }

        if (dto.getItemDtos() != null && !dto.getItemDtos().isEmpty()) {
            entity.setItems(ConverterUtils.convertToEntities(itemDtoConverter, dto.getItemDtos()));
        }

        return entity;
    }
}
