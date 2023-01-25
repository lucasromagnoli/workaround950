package br.com.lucasromagnoli.workaround.tictoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.*;

@Builder
@Getter
public class GameDetailsResponseDTO {

    @JsonProperty("game_id")
    private UUID gameId;

    private UUID turn;

    private UUID player1;

    private UUID player2;

    private String status;

    private UUID winner;

    private Map<Position, Optional<Player>> tiles;
    public static GameDetailsResponseDTO from(Game game) {
        return GameDetailsResponseDTO.builder()
                .gameId(game.getId())
                .status(game.getStatus().name())
                .winner(Optional.of(game).map(Game::getWinnerPlayer).map(Player::getId).orElse(null))
                .turn(game.getTurn().getId())
                .player1(game.getPlayer1().getId())
                .player2(game.getPlayer2().getId())
                .tiles(game.getBoard().tilesByPlayer())
                .build();
    }

    private static class BoardDetailsResponseDTO {


        public BoardDetailsResponseDTO(Board board) {
            board.tiles();
        }
    }
}
