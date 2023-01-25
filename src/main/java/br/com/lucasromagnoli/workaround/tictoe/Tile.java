package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tile {

    @JsonProperty("player")
    private Player player;

    @ToString.Exclude
    @JsonProperty("posicao")
    private final Position position;
}
