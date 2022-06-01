package bestseller.test.game.service;

import bestseller.test.game.entity.Game;
import bestseller.test.game.dao.GameRepoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private GameRepoDAO gameRepoDAO;

    public Game findById(Integer id) {
        Optional<Game> output = gameRepoDAO.findById(id);
        return output.isPresent() ? output.get() : null;
    }

    public List<Game> getAllGames() {
        return (List<Game>) gameRepoDAO.findAll();
    }

    public Game createGame(Game game) {
        game.setDateCreated(LocalDateTime.now());
        return gameRepoDAO.save(game);
    }

    public List<Game> createDummyGames() {
        List<Game> data = (List<Game>) gameRepoDAO.findAll();

        if (data.size() == 0) {
            List<Game> games = new ArrayList<>();
            games.add(createGameObj("fortnite"));
            games.add(createGameObj("call of duty"));
            games.add(createGameObj("dota"));
            games.add(createGameObj("valhalla"));
            games.add(createGameObj("amongus"));
            data = (List<Game>) gameRepoDAO.saveAll(games);
        }
        return data;
    }

    private Game createGameObj(String gameName) {
        Game game = new Game();
        game.setName(gameName);
        game.setDateCreated(LocalDateTime.now());
        return game;
    }
}
