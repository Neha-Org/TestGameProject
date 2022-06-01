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
@Table(name = "user")
public class User {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String geography;

    @Column
    private LocalDateTime dateCreated;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    Set<UserGameLevel> userGameLevelSet;

}
