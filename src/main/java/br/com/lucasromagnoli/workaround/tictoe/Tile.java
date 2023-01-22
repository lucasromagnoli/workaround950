package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tile {
    private Player player;

    @ToString.Exclude
    private final Position position;
}
