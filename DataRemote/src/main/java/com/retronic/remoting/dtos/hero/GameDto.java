package com.retronic.remoting.dtos.hero;

import java.io.Serializable;
import java.util.List;

public class GameDto implements Serializable {

    private static final long serialVersionUID = 2405172041950251824L;

    public static final String URL_REPRESENTATION = "games";

    private Integer id;

    private String name;

    private Integer difficult;

    private String secret;

    private Integer userId;

    private List<CharacterDto> characterDtos;

    private String characterReference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<CharacterDto> getCharacterDtos() {
        return characterDtos;
    }

    public void setCharacterDtos(List<CharacterDto> characterDtos) {
        this.characterDtos = characterDtos;
    }

    public String getCharacterReference() {
        return characterReference;
    }

    public void setCharacterReference(String characterReference) {
        this.characterReference = characterReference;
    }
}
