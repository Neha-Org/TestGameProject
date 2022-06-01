package bestseller.test.game.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaxCreditForLevels {

    private Integer userId;
    private String userName;
    private Integer gameId;
    private String gameName;
    private Long credit;
    private String level;

}
