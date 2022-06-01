package bestseller.test.game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name ="Game")
public class Game {
    @Column
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
    Set<UserGameLevel> userGameLevelSet;

}
