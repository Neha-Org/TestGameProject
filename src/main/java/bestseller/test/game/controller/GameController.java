package bestseller.test.game.controller;

import bestseller.test.game.entity.Game;
import bestseller.test.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/game/{id}")
    public Game findById(@PathVariable Integer id) {
        return gameService.findById(id);
    }

    @PostMapping("/game")
    public Game createGame(@RequestBody Game game) {
        return gameService.createGame(game);
    }

    @GetMapping("/games")
    public List<Game> v() {
        return gameService.getAllGames();
    }

    @PostMapping("/createdummygames")
    public List<Game> createDummyGames(@RequestBody List<Game> games) {
        return gameService.createDummyGames();
    }

}
