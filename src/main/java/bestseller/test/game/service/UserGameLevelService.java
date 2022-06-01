package bestseller.test.game.service;

import bestseller.test.game.entity.Game;
import bestseller.test.game.entity.User;
import bestseller.test.game.entity.UserGameLevel;
import bestseller.test.game.entity.UserGameLevelKey;
import bestseller.test.game.dao.GameRepoDAO;
import bestseller.test.game.dao.UserGameLevelDAO;
import bestseller.test.game.dao.UserRepoDAO;
import bestseller.test.game.pojo.GameLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGameLevelService {

    @Autowired
    private UserGameLevelDAO userGameLevelDAO;

    @Autowired
    private GameRepoDAO gameRepoDAO;

    @Autowired
    private UserRepoDAO userRepoDAO;

    public List<GameLevel> getUserGameLevel() {
        List<UserGameLevel> userGameLevelList = (List<UserGameLevel>) userGameLevelDAO.findAll();
        return null;
    }

    public void saveUserGameLevel(GameLevel gameLevel) throws Exception {
        Optional<Game> game = gameRepoDAO.findById(gameLevel.getGameId());
        Optional<User> user = userRepoDAO.findById(gameLevel.getUserId());
        if(game.isEmpty() || user.isEmpty()) {
            throw new Exception("Invalid Game/User");
        }
        UserGameLevel userGameLevel = new UserGameLevel();
        userGameLevel.setId(new UserGameLevelKey(gameLevel.getUserId(), gameLevel.getGameId()));
        userGameLevel.setGame(gameRepoDAO.findById(gameLevel.getGameId()).get());
        userGameLevel.setUser(userRepoDAO.findById(gameLevel.getUserId()).get());
        userGameLevel.setLevel(gameLevel.getLevel().name());
        userGameLevelDAO.save(userGameLevel);
    }

}
