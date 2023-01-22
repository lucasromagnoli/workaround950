package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Set;

import static br.com.lucasromagnoli.workaround.tictoe.Position.*;

@Getter
@RequiredArgsConstructor
public enum Rules {
    VERTICAL_LEFT(Set.of(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT)),
    VERTICAL_CENTER(Set.of(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER)),
    VERTICAL_RIGHT(Set.of(BOTTOM_LEFT, MIDDLE_LEFT, TOP_LEFT)),
    HORIZONTAL_TOP(Set.of(TOP_LEFT, TOP_CENTER, TOP_RIGHT)),
    HORIZONTAL_CENTER(Set.of(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT)),
    HORIZONTAL_BOTTOM(Set.of(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT)),
    DIAGONAL_LEFT(Set.of(BOTTOM_LEFT, MIDDLE_CENTER, TOP_RIGHT)),
    DIAGONAL_RIGHT(Set.of(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT));

    private final Set<Position> positionsToWin;

    public static Rules getMatchedRule(Set<Position> playerPositions) {
        return Arrays.stream(values())
                .filter(rule -> playerPositions.containsAll(rule.getPositionsToWin()))
                .findFirst()
                .orElse(null);
    }
}
