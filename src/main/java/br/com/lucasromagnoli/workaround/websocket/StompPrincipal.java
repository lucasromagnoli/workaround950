package br.com.lucasromagnoli.workaround.websocket;

import br.com.lucasromagnoli.workaround.tictoe.Player;
import lombok.Getter;

import java.security.Principal;
import java.util.UUID;

public class StompPrincipal implements Principal {
    private final String name;

    @Getter
    private final Player player;

    public StompPrincipal(String name) {
        this.name = name;
        this.player = new Player(UUID.fromString(name));
    }

    @Override
    public String getName() {
        return name;
    }
}