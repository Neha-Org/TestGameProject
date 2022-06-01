package bestseller.test.game.controller;

import bestseller.test.game.pojo.GameSearchDetails;
import bestseller.test.game.service.GameSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GameSearchController {

    @Autowired
    private GameSearchService gameSearchService;

    @GetMapping("/search")
    public List<GameSearchDetails> searchByParam(@RequestParam Optional<String> level,
                                                 @RequestParam Optional<String> gameName,
                                                 @RequestParam Optional<String> geography) throws Exception {
        return gameSearchService.searchByParam(level, gameName, geography);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleException() {
        return new ResponseEntity<Object>(
                "No Data Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
