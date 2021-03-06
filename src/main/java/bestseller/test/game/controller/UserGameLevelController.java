package bestseller.test.game.controller;

import bestseller.test.game.pojo.GameLevel;
import bestseller.test.game.service.UserGameLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGameLevelController {
    @Autowired
    private UserGameLevelService userGameService;

    @GetMapping("/UserGameLevels")
    public List<GameLevel> getAllUserGameLevel() {
        return userGameService.getUserGameLevel();
    }

    @PostMapping("/user/level")
    public ResponseEntity<?> createUserGameLevel(@RequestBody GameLevel gameLevel) throws Exception {
        return new ResponseEntity<GameLevel>(userGameService.saveUserGameLevel(gameLevel), HttpStatus.CREATED);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<Object>(
                "Invalid Parameters", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
