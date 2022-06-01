package bestseller.test.game.dao;

import bestseller.test.game.entity.UserGameLevel;
import bestseller.test.game.entity.UserGameLevelKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserGameLevelDAO extends CrudRepository<UserGameLevel, UserGameLevelKey> {

    @Query("SELECT u FROM UserGameLevel u WHERE u.user.geography = ?1")
    Collection<UserGameLevel> findByGeography(String geography);

    @Query("SELECT u FROM UserGameLevel u WHERE u.level = ?1")
    Collection<UserGameLevel> findByLevel(String level);

    @Query("SELECT u FROM UserGameLevel u WHERE u.game.name = ?1")
    Collection<UserGameLevel> findByGameName(String gameName);

    @Query("SELECT u FROM UserGameLevel u WHERE u.user.geography=?1 and u.level=?2 and u.game.name = ?3")
    Collection<UserGameLevel> findByGeographyLevelAndGameName(String geography, String level, String gameName);

    @Query("SELECT u FROM UserGameLevel u WHERE u.level=?1 and u.game.name = ?2")
    Collection<UserGameLevel> findByLevelAndGameName(String level, String gameName);

    @Query("SELECT u FROM UserGameLevel u WHERE u.level=?1 and u.user.geography=?2")
    Collection<UserGameLevel> findByLevelAndGeography(String level, String geography);

    @Query("SELECT u FROM UserGameLevel u WHERE u.game.name=?1 and u.user.geography=?2")
    Collection<UserGameLevel> findByGameNameAndGeography(String gameName, String geography);

//    @Query("SELECT u FROM UserGameLevel u WHERE (u.user.geography IS NULL OR u.user.geography=?1) and u.level=?2 and u.game.name = ?3")
//    Collection<UserGameLevel> findAllByGeographyLevelAndGameName(String geography, String level, String gameName);


}
