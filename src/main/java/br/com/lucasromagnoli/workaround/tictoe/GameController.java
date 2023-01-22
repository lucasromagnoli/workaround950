package br.com.lucasromagnoli.workaround.tictoe;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/tictoe")
@RequiredArgsConstructor
public class GameController {


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

        game.doAction(position);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
