package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Setter;
import lombok.ToString;

import java.util.*;

import static br.com.lucasromagnoli.workaround.tictoe.Position.*;

@Setter
@ToString
public class Board {
    private final Map<Position, Tile> tiles = new LinkedHashMap<>();

    public Board() {
        this.tiles.put(TOP_LEFT, new Tile(TOP_LEFT));
        this.tiles.put(TOP_CENTER, new Tile(TOP_CENTER));
        this.tiles.put(TOP_RIGHT, new Tile(TOP_RIGHT));
        this.tiles.put(MIDDLE_LEFT, new Tile(MIDDLE_LEFT));
        this.tiles.put(MIDDLE_CENTER, new Tile(MIDDLE_CENTER));
        this.tiles.put(MIDDLE_RIGHT, new Tile(MIDDLE_RIGHT));
        this.tiles.put(BOTTOM_LEFT, new Tile(BOTTOM_LEFT));
        this.tiles.put(BOTTOM_CENTER, new Tile(BOTTOM_CENTER));
        this.tiles.put(BOTTOM_RIGHT, new Tile(BOTTOM_RIGHT));
    }

    public Set<Tile> tiles() {
        return new LinkedHashSet<>(this.tiles.values());
    }

    public Tile tile(Position position) {
        return Optional.ofNullable(this.tiles.get(position)).orElseThrow();
    }
}
