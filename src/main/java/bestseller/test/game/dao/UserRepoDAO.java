package bestseller.test.game.dao;

import bestseller.test.game.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoDAO extends CrudRepository<User, Integer> {
}
