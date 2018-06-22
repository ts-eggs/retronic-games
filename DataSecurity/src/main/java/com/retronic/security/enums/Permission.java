package com.retronic.security.enums;

public enum Permission {
    IS_USER, USER_READ, USER_WRITE,     // core.user
    ROLE_READ, ROLE_WRITE,              // core.role
    COUNTRY_READ, COUNTRY_WRITE,        // core.country
    SKILL_READ, SKILL_WRITE,            // hero.skill
    ARMOR_READ, ARMOR_WRITE,            // hero.armor
    RACE_READ, RACE_WRITE,              // hero.race
    CLASS_READ, CLASS_WRITE,            // hero.class
    ITEM_READ, ITEM_WRITE,              // hero.item
    WEAPON_READ, WEAPON_WRITE,          // hero.weapon
    CHARACTER_READ, CHARACTER_WRITE,    // hero.character
    GAME_READ, GAME_WRITE;              // hero.game
}
