package bestseller.test.game.service;

import bestseller.test.game.dao.UserGameLevelDAO;
import bestseller.test.game.entity.UserGameLevel;
import bestseller.test.game.pojo.GameSearchDetails;
import bestseller.test.game.pojo.GameSearchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameSearchService {

    @Autowired
    private UserGameLevelDAO userGameLevelDAO;

    private List<GameSearchDetails> mapToGameSearchDetailsOutput(List<UserGameLevel> data) {
        Map<Integer, GameSearchDetails> gameSearchDetailsHashMap = new HashMap<>();
        data.forEach(s -> {
            if (gameSearchDetailsHashMap.containsKey(s.getGame().getId())) {
                GameSearchDetails gameSearchDetailsFromMap = gameSearchDetailsHashMap.get(s.getGame().getId());
                GameSearchUser gameSearchUser = copyGameSearchUser(s);
                List<GameSearchUser> gameSearchUserList = gameSearchDetailsFromMap.getGameSearchUsers();
                gameSearchUserList.add(gameSearchUser);
                gameSearchDetailsHashMap.put(s.getGame().getId(), gameSearchDetailsFromMap);
            } else {
                GameSearchDetails gameSearchDetails = new GameSearchDetails();
                gameSearchDetails.setGameId(s.getGame().getId());
                gameSearchDetails.setGameName(s.getGame().getName());
                GameSearchUser gameSearchUser = copyGameSearchUser(s);
                List<GameSearchUser> gameSearchUserList = new ArrayList<>();
                gameSearchUserList.add(gameSearchUser);
                gameSearchDetails.setGameSearchUsers(gameSearchUserList);
                gameSearchDetailsHashMap.put(s.getGame().getId(), gameSearchDetails);
            }
        });
        return new ArrayList<>(gameSearchDetailsHashMap.values());
    }

    private GameSearchUser copyGameSearchUser(UserGameLevel s) {
        GameSearchUser gameSearchUser = new GameSearchUser();
        gameSearchUser.setUserId(s.getUser().getId());
        gameSearchUser.setUserName(s.getUser().getName());
        gameSearchUser.setNickname(s.getUser().getNickname());
        gameSearchUser.setGeography(s.getUser().getGeography());
        gameSearchUser.setLevel(s.getLevel());
        return gameSearchUser;
    }

    public List<GameSearchDetails> searchByParam(Optional<String> level, Optional<String> gameName, Optional<String> geography) throws Exception {
        List<UserGameLevel> data = null;
        if (level.isPresent() && gameName.isPresent() && geography.isPresent()) {
            data = (List<UserGameLevel>) userGameLevelDAO.findByGeographyLevelAndGameName(geography.get(), level.get(), gameName.get());
        } else if (level.isPresent() && gameName.isPresent()) {
            data = (List<UserGameLevel>) userGameLevelDAO.findByLevelAndGameName(level.get(), gameName.get());
        } else if (level.isPresent() && geography.isPresent()) {
            data = (List<UserGameLevel>) userGameLevelDAO.findByLevelAndGeography(level.get(), geography.get());
        } else if (gameName.isPresent() && geography.isPresent()) {
            data = (List<UserGameLevel>) userGameLevelDAO.findByGameNameAndGeography(gameName.get(), geography.get());
        } else if (level.isPresent())
            data = (List<UserGameLevel>) userGameLevelDAO.findByLevel(level.get());
        else if (gameName.isPresent())
            data = (List<UserGameLevel>) userGameLevelDAO.findByGameName(gameName.get());
        else if (geography.isPresent())
            data = (List<UserGameLevel>) userGameLevelDAO.findByGeography(geography.get());
        else if (level.isEmpty() && gameName.isEmpty() && geography.isEmpty()) {
            data = (List<UserGameLevel>) userGameLevelDAO.findAll();
        }

        if (null == data) {
            throw new Exception("No Data Found");
        }

        return mapToGameSearchDetailsOutput(data);
    }
}
