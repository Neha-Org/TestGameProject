package bestseller.test.game.controller;

import bestseller.test.game.entity.Game;
import bestseller.test.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<Game> game = gameService.findById(id);
        if (game.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No game found");
        return new ResponseEntity<Game>(game.get(), HttpStatus.OK);
    }

    @PostMapping("/game")
    public ResponseEntity<?> createGame(@RequestBody Game game) {
        return new ResponseEntity<Game>(gameService.createGame(game), HttpStatus.CREATED);
    }

    @GetMapping("/games")
    public ResponseEntity<?> getAllGames() {
        List<Game> games = gameService.getAllGames();
        if (games.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No games found");
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }

    @PostMapping("/createdummygames")
    public ResponseEntity<?> createDummyGames(@RequestBody List<Game> games) {
        return new ResponseEntity<List<Game>>(gameService.createDummyGames(), HttpStatus.CREATED);
    }

}
