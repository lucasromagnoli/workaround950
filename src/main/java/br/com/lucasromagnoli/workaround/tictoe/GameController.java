package br.com.lucasromagnoli.workaround.tictoe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/tictoe")
@RequiredArgsConstructor
public class GameController {

    private final SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/player_login")
    @SendToUser("/tictoe/player_info")
//    @SendToUser("/topic/greetings")
    public UUID greeting(Player player) {
//        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!"));
        UglySession.setPlayer(player);
        return player.getId();
    }

    @MessageMapping("/create")
    public void lobby(String player2Id, Player player1) {
        Player player2 = UglySession.getPlayer(player2Id);
        Game game = new Game(player1, player2);
        UglySession.putGame(game);
        this.render(game);
    }

    @MessageMapping("/player_action")
    public void action(String position, Player player) {

        Game game = UglySession.getGame(player);

        game.doAction(player, Position.valueOf(position));
        this.render(game);
    }

    private void render(Game game) {
        GameDetailsResponseDTO render = GameDetailsResponseDTO.from(game);
        simpMessagingTemplate.convertAndSendToUser(game.getPlayer1().getName(), "/tictoe/game", render);
        simpMessagingTemplate.convertAndSendToUser(game.getPlayer2().getName(), "/tictoe/game", render);
    }


    @PostMapping("/create")
    public ResponseEntity<CreatedGameResponseDTO> createGame(@RequestBody CreateGameRequestDTO createGameRequestDTO) {

        Player player1 = UglySession.getPlayer(createGameRequestDTO.getPlayer1Id());
        Player player2 = UglySession.getPlayer(createGameRequestDTO.getPlayer2Id());

        Game createdGame = new Game(player1, player2);

        UglySession.putGame(createdGame);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreatedGameResponseDTO.from(createdGame));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<GameDetailsResposneDTO> detailsGame(@PathVariable("id") String id) {
        Game game = UglySession.getGame(id);

        return ResponseEntity.ok(new GameDetailsResposneDTO(game));
    }

    @PostMapping("/action")
    public ResponseEntity<Void> action(@RequestBody PlayerActionRequestDTO playerActionRequestDTO) {
        Player player = UglySession.getPlayer(playerActionRequestDTO.getId());
        Position position = Position.valueOf(playerActionRequestDTO.getPosition());
        Game game = UglySession.getGame(player);

        game.doAction(player, position);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
