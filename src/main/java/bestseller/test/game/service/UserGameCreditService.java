package bestseller.test.game.service;

import bestseller.test.game.dao.UserGameCreditDAO;
import bestseller.test.game.dao.UserGameLevelDAO;
import bestseller.test.game.entity.UserGameCredit;
import bestseller.test.game.entity.UserGameLevel;
import bestseller.test.game.entity.UserGameLevelKey;
import bestseller.test.game.pojo.GameCredit;
import bestseller.test.game.pojo.MaxCreditForLevels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserGameCreditService {

    @Autowired
    private UserGameCreditDAO userGameCreditDAO;

    @Autowired
    private UserGameLevelDAO userGameLevelDAO;

    public GameCredit saveUserGameCredit(GameCredit gameCredit) throws Exception {
        //check if game exist for the user, if not throw error
        Optional<UserGameLevel> data = userGameLevelDAO.findById(new UserGameLevelKey(gameCredit.getUserId(), gameCredit.getGameId()));
        if(data.isPresent()) {
            UserGameCredit userGameCredit = new UserGameCredit();
            userGameCredit.setCredit(gameCredit.getCredit());
            userGameCredit.setGameId(gameCredit.getGameId());
            userGameCredit.setUserId(gameCredit.getUserId());
            userGameCredit.setDateCreated(LocalDateTime.now());
            userGameCredit.setDateUpdated(LocalDateTime.now());
            userGameCreditDAO.save(userGameCredit);
        } else {
            throw new Exception("Invalid game/user");
        }
        return gameCredit;
    }

    public List<MaxCreditForLevels> getMaxCreditForGameByLevel() {
        List<MaxCreditForLevels> maxCreditForAllLevels = userGameCreditDAO.getMaxCreditForGameByLevel();

        return Stream.of(getMaxCreditForLevels(maxCreditForAllLevels, "PRO")
                        , getMaxCreditForLevels(maxCreditForAllLevels, "NOOB")
                        , getMaxCreditForLevels(maxCreditForAllLevels, "INVINCIBLE"))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<MaxCreditForLevels> getMaxCreditForLevels(List<MaxCreditForLevels> maxCreditForLevels, String PRO) {
        List<MaxCreditForLevels> maxCreditForProLevels;
        List<MaxCreditForLevels> proData = maxCreditForLevels.stream().filter(s -> s.getLevel().equals(PRO)).collect(Collectors.toList());
        Map<Integer, MaxCreditForLevels> dataMaxValueMap = new HashMap<>();
        proData.stream().forEach(
                s -> {
                    if (dataMaxValueMap.containsKey(s.getGameId())) {
                        MaxCreditForLevels m = dataMaxValueMap.get(s.getGameId());
                        if (s.getCredit() > m.getCredit()) {
                            dataMaxValueMap.put(s.getGameId(), s);
                        }
                    } else {
                        dataMaxValueMap.put(s.getGameId(), s);
                    }
                }
        );
        maxCreditForProLevels = null != dataMaxValueMap ? new ArrayList<>(dataMaxValueMap.values()) : new ArrayList<>();
        return maxCreditForProLevels;
    }
}
