package bestseller.test.game.service;

import bestseller.test.game.entity.User;
import bestseller.test.game.dao.UserRepoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepoDAO userRepoDAO;

    public Optional<User> findById(Integer id) {
        return userRepoDAO.findById(id);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepoDAO.findAll();
    }

    public User createUser(User user) {
        user.setDateCreated(LocalDateTime.now());
        return userRepoDAO.save(user);
    }

}
