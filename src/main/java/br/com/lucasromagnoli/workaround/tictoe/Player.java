package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
public class Player {
    private final UUID id;
    private final String name;

    public Player(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public Player(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
