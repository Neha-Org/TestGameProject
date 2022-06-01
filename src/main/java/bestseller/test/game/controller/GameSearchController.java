package bestseller.test.game.controller;

import bestseller.test.game.pojo.GameSearchDetails;
import bestseller.test.game.service.GameSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class GameSearchController {

    @Autowired
    private GameSearchService gameSearchService;

    @GetMapping("/search")
    public ResponseEntity<?> searchByParam(@RequestParam Optional<String> level,
                                           @RequestParam Optional<String> gameName,
                                           @RequestParam Optional<String> geography) throws Exception {
        List<GameSearchDetails> gameSearchDetailsList = gameSearchService.searchByParam(level, gameName, geography);
        if (gameSearchDetailsList.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data found");
        return new ResponseEntity<List<GameSearchDetails>>(gameSearchDetailsList, HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException() {
        return new ResponseEntity<Object>(
                "No Data Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
