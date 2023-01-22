package br.com.lucasromagnoli.workaround.tictoe;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class UglySession {

    private static final Map<UUID, Game> UGLY_SESSION_GAMES = new HashMap<>();
    private static final Map<UUID, Player> UGLY_SESSION_PLAYERS = new HashMap<>();
    private static final Map<Player, Game> UGLY_SESSION_MATCHES = new HashMap<>();

    static {
        UUID player1Id = UUID.fromString("c63fdc47-9a77-4a57-b5c0-3577247b58e1");
        UUID player2Id = UUID.fromString("4625d5d8-8555-4a1e-9c00-1cdd51e16b85");
        UGLY_SESSION_PLAYERS.put(player1Id, new Player(player1Id, "Lucas"));
        UGLY_SESSION_PLAYERS.put(player2Id, new Player(player2Id, "Artur"));
        log.info("Created ugly players [{}]", UGLY_SESSION_PLAYERS);
    }

    public static void putGame(Game game) {
        UGLY_SESSION_GAMES.put(game.getId(), game);
        UGLY_SESSION_MATCHES.put(game.getPlayer1(), game);
        UGLY_SESSION_MATCHES.put(game.getPlayer2(), game);
    }

    public static Game getGame(String id) {
        return UGLY_SESSION_GAMES.get(UUID.fromString(id));
    }

    public static Game getGame(Player player) {
        return UGLY_SESSION_MATCHES.get(player);
    }

    public static Player getPlayer(String id) {
        return Optional.ofNullable(UGLY_SESSION_PLAYERS.get(UUID.fromString(id))).orElseThrow();
    }

}
