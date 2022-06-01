package bestseller.test.game.controller;

import bestseller.test.game.pojo.GameCredit;
import bestseller.test.game.pojo.MaxCreditForLevels;
import bestseller.test.game.service.UserGameCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserGameCreditController {

    @Autowired
    private UserGameCreditService userGameCreditService;

    @PostMapping("/user/credit")
    public GameCredit createUserGameLevel(@RequestBody GameCredit gameCredit) throws Exception {
        return userGameCreditService.saveUserGameCredit(gameCredit);
    }

    @GetMapping("/user/max_credit")
    public List<MaxCreditForLevels> getMaxCreditForGameByLevel() {
        return userGameCreditService.getMaxCreditForGameByLevel();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<Object>(
                "Invalid Parameters", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
