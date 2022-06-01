package bestseller.test.game.controller;

import bestseller.test.game.entity.Game;
import bestseller.test.game.service.GameService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@SpringBootTest
public class GameControllerTest {

    @Autowired
    private GameController gameController;

    @Mock
    private GameService gameService;

    @Test
    public void testAddEmployee() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Game game = new Game(1, "TestGame", LocalDateTime.now(), null);
        Mockito.when(gameService.createGame(game)).thenReturn(game);
        ResponseEntity<?> responseEntity = gameController.createGame(game);
        Game output = (Game) responseEntity.getBody();
        Assertions.assertEquals(responseEntity.getStatusCodeValue(), 201);
        Assertions.assertEquals(output.getId(), game.getId());

    }
}
