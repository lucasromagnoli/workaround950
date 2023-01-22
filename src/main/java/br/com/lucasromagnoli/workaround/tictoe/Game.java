package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.lucasromagnoli.workaround.tictoe.Position.*;

@Slf4j
@Getter
@Setter
@ToString
public class Game {

    private final UUID id = UUID.randomUUID();

    private Player turn;
    private final Board board = new Board();
    private final Player player1;
    private final Player player2;

    public Game(Player player1, Player player2) {
        this.turn = player1;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void doAction(Position position) {
        log.info("Executando turno do {}", turn.getName());
        this.action(board.tile(position));
        this.rules();
        this.nextTurn();
    }

    private void action(Tile tile) {
        tile.setPlayer(turn);
    }

    private void nextTurn() {
        if (this.player1.equals(turn)) {
            turn = player2;
        } else {
            turn = player1;
        }

        log.info("O turno é: [{}]", turn.getName());
    }

    public void rules() {
        Map<Player, List<Tile>> tilesByPlayers = this.board.tiles().stream()
                .filter(tile -> Objects.nonNull(tile.getPlayer()))
                .collect(Collectors.groupingBy(Tile::getPlayer));

        tilesByPlayers.forEach((player, tiles) -> {
            Set<Position> positions = tiles.stream().map(Tile::getPosition).collect(Collectors.toSet());
            Optional.ofNullable(Rules.getMatchedRule(positions))
                    .ifPresent(rules -> log.info("Jogador [{}] venceu com [{}]", player.getName(), rules.name()));
        });
    }

    public static void main(String[] args) {
        Player player1 = new Player("Lucas");
        Player player2 = new Player("Arthur");

        Game game = new Game(player1, player2);


        game.setTurn(player1);

        game.doAction(TOP_LEFT);
        game.doAction(MIDDLE_LEFT);
        game.doAction(TOP_CENTER);
        game.doAction(MIDDLE_CENTER);
        game.doAction(TOP_RIGHT);
    }
}
