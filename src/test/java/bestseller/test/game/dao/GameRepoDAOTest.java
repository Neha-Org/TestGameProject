package bestseller.test.game.dao;

import bestseller.test.game.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;

@SpringBootTest
public class GameRepoDAOTest {

    @Autowired
    private GameRepoDAO gameRepoDAO;

    @Test
    public void isGameCreatedById() {
        Game game = new Game();
        game = gameRepoDAO.save(game);
        Optional<Game> gameOutput = gameRepoDAO.findById(game.getId());
        assertThat(gameOutput.isPresent()).isTrue();
    }

}
