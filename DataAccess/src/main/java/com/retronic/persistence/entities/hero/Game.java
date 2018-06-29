package com.retronic.persistence.entities.hero;

import com.retronic.persistence.entities.core.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "game", schema = "rgh")
public class Game implements Serializable {

    private static final long serialVersionUID = 2405172041950251813L;

    @Id
    @Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
    private Integer id;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "difficult", nullable = true)
    private Integer difficult;

    @Column(name = "secret", nullable = true)
    private String secret;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fkUserId")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rgh.gameCharacterRef", joinColumns = {@JoinColumn(name = "fkGameId")}, inverseJoinColumns = {@JoinColumn(name = "fkCharacterId")})
    private List<Character> characters;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }
}
