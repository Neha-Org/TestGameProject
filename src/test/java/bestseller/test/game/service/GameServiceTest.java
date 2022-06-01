package bestseller.test.game.service;

import bestseller.test.game.dao.GameRepoDAO;
import bestseller.test.game.entity.Game;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GameServiceTest {
    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepoDAO gameRepoDAO;

    @Test
    public void testCreateDummyGames() {
        Mockito.when(gameRepoDAO.findAll()).thenReturn(testData());
        List<Game> output = gameService.createDummyGames();
        Assert.isTrue(output.size() == 5, "Size should be 5");
    }

    @Test
    public void getCreateGame() {
        Game game = new Game();
        game.setId(1);
        Mockito.when(gameRepoDAO.save(game)).thenReturn(game);
        Game output = gameService.createGame(game);
        Assert.isTrue(output.getId() != null, "Game is created");
    }

    private List<Game> testData() {
        List<Game> games = new ArrayList<>();
        games.add(createGameObj("fortnite"));
        games.add(createGameObj("call of duty"));
        games.add(createGameObj("dota"));
        games.add(createGameObj("valhalla"));
        games.add(createGameObj("amongus"));
        return games;
    }

    private Game createGameObj(String gameName) {
        Game game = new Game();
        game.setName(gameName);
        game.setDateCreated(LocalDateTime.now());
        return game;
    }

}
