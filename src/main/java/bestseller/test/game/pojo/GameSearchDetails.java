package bestseller.test.game.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameSearchDetails {
    private Integer gameId;
    private String gameName;
    List<GameSearchUser> gameSearchUsers;
}
