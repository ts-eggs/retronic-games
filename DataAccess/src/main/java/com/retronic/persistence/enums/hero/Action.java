package com.retronic.persistence.enums.hero;

public enum Action {

    ADD_ATTACK(0, "Add value to attack."),
    ADD_DEFENSE(1, "Add value to defense."),
    MULTIPLY_ATTACK(2, "Multiply value with attack."),
    MULTIPLY_DEFENSE(3, "Multiply value with defense."),
    ADD_VITALITY(4, "Add value to vitality."),
    ADD_DEXTERITY(5, "Add value to dexterity."),
    ADD_STRENGTH(6, "Add value to strength."),
    ADD_MOVEMENT(7, "Add value to movement."),
    ADD_ASSISTANT(8, "Add value to assistant."),
    REMOVE_VITALITY(9, "Remove value from vitality."),
    REMOVE_ASSISTANT(10, "Remove an assistant."),
    SPECIAL_ARMOR(11, "Can wear special armor."),
    SPECIAL_WEAPONS(12, "Can wear special weapons."),
    NO_SKILLS(13, "Can´t use skill for one round."),
    NO_SACRIFICE(14, "Can´t use the help of assistants to avoid death."),
    ESCAPE_EASY(15, "Can use better escape dice."),
    ESCAPE(16, "Escape from fight."),
    STEAL(17, "Can steal from characters."),
    COMBINED_ATTACK_SACRIFICE(20, "Combination of 0 and 14."),
    COMBINED_ATTACK_VITALITY(21, "Combination of 0 & 9");

    private final int id;
    private final String description;

    Action(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static Action getById(int id) {
        for(Action action : values()) {
            if(action.id == id) return action;
        }

        return ADD_ATTACK;
    }
}
