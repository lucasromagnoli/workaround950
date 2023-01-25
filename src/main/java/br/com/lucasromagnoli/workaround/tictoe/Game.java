package br.com.lucasromagnoli.workaround.tictoe;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.lucasromagnoli.workaround.tictoe.Position.*;
import static br.com.lucasromagnoli.workaround.tictoe.Status.RUNNING;

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
    private Player winnerPlayer;
    private Status status;

    public Game(Player player1, Player player2) {
        this.turn = player1;
        this.player1 = player1;
        this.player2 = player2;
        this.status = RUNNING;
    }

    public void doAction(Player player, Position position) {
        if (RUNNING.equals(this.status) && this.turn.equals(player)) {
            log.info("Executando turno do {}", turn.getName());
            this.action(board.tile(position));

            this.checkRules();

            if (Objects.nonNull(this.winnerPlayer)) {
                this.endGame();
                return;
            }

            this.nextTurn();
        }
    }

    private void endGame() {
        log.info("Jogador [{}] venceu !", this.winnerPlayer.getName());
        this.status = Status.ENDED;
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

    public void checkRules() {
        Map<Player, List<Tile>> tilesByPlayers = this.board.tiles()
                .stream()
                .filter(tile -> Objects.nonNull(tile.getPlayer()))
                .collect(Collectors.groupingBy(Tile::getPlayer));

        this.winnerPlayer = tilesByPlayers
                .entrySet()
                .stream()
                .filter(playerListEntry -> {
                    List<Tile> tiles = playerListEntry.getValue();
                    Set<Position> positions = tiles
                            .stream()
                            .map(Tile::getPosition)
                            .collect(Collectors.toSet());

                    return Objects.nonNull(Rules.getMatchedRule(positions));
                })
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public static void main(String[] args) {
//        Player player1 = new Player("Lucas");
//        Player player2 = new Player("Arthur");
//
//        Game game = new Game(player1, player2);
//
//        game.setTurn(player1);
//
//        game.doAction(player1, TOP_LEFT);
//        game.doAction(player1, MIDDLE_LEFT);
//        game.doAction(player1, TOP_CENTER);
//        game.doAction(player1, MIDDLE_CENTER);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
//        game.doAction(player1, TOP_RIGHT);
    }
}
