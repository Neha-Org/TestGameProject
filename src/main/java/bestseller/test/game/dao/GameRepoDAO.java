package bestseller.test.game.dao;

import bestseller.test.game.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepoDAO extends CrudRepository<Game, Integer> {
}
