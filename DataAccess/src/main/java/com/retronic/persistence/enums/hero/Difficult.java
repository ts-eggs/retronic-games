package com.retronic.persistence.enums.hero;

public enum Difficult {

    EASY(1, "Easy"),
    NORMAL(2, "Normal"),
    HARD(3, "Hard");

    private final int id;
    private final String name;

    Difficult(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
