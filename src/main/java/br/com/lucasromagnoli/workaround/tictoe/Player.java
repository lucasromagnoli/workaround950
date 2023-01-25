package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.security.Principal;
import java.util.UUID;

@ToString
@RequiredArgsConstructor
public class Player implements Principal {
    @Getter
    @JsonProperty("player_id")
    private final UUID id;

    @Override
    @JsonIgnore
    public String getName() {
        return this.id.toString();
    }
}
