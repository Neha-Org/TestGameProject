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

    public User findById(Integer id) {
        Optional<User> output = userRepoDAO.findById(id);
        return output.isPresent() ? output.get() : null;
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepoDAO.findAll();
    }

    public User createUser(User user) {
        user.setDateCreated(LocalDateTime.now());
        return userRepoDAO.save(user);
    }

    public void deleteUser(User user) {
        userRepoDAO.delete(user);
    }
}
