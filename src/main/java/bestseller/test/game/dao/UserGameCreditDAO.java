package bestseller.test.game.dao;

import bestseller.test.game.entity.UserGameCredit;
import bestseller.test.game.pojo.MaxCreditForLevels;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGameCreditDAO extends CrudRepository<UserGameCredit, Integer> {

    @Query("select new bestseller.test.game.pojo.MaxCreditForLevels(gl.id.userId, gl.user.name, gl.id.gameId, gl.game.name, max(gc.credit), gl.level) " +
            "from UserGameCredit gc INNER JOIN UserGameLevel gl " +
            "ON gc.userId = gl.id.userId AND gc.gameId=gl.id.gameId " +
            "GROUP BY (gl.id.gameId, gl.id.userId, gl.level)")
    public List<MaxCreditForLevels> getMaxCreditForGameByLevel();
}
