package bestseller.test.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_game_credit")
public class UserGameCredit {
    @Column
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "credit")
    private Long credit;

    @Column
    private LocalDateTime dateCreated;

    @Column
    private LocalDateTime dateUpdated;

}
