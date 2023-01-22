package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Setter;
import lombok.ToString;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static br.com.lucasromagnoli.workaround.tictoe.Position.values;

@Setter
@ToString
public class Board {
    private final Map<Position, Tile> tiles = Arrays.stream(values())
            .map(Tile::new)
            .collect(Collectors.toMap(Tile::getPosition, Function.identity()));

    public Set<Tile> tiles() {
        return new LinkedHashSet<>(this.tiles.values());
    }

    public Tile tile(Position position) {
        return Optional.ofNullable(this.tiles.get(position)).orElseThrow();
    }
}
