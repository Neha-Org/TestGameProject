package bestseller.test.game.service;

import bestseller.test.game.dao.UserRepoDAO;
import bestseller.test.game.entity.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepoDAO userRepoDAO;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId(1);
        Mockito.when(userRepoDAO.save(user)).thenReturn(user);
        User output = userService.createUser(user);
        Assert.isTrue(output.getId() != null, "User is created");
    }

    @Test
    public void testGetAllUser() {
        User user1 = new User();
        user1.setId(1);
        User user2 = new User();
        user2.setId(2);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        Mockito.when(userRepoDAO.findAll()).thenReturn(users);
        List<User> output = userService.getAllUsers();
        Assert.isTrue(output.size() == 2, "Two users fetched");
    }

    @Test
    public void testFindUserById() {
        User user1 = new User();
        user1.setId(1);
        Mockito.when(userRepoDAO.findById(1)).thenReturn(Optional.of(user1));
        Optional<User> output = userService.findById(1);
        Assert.isTrue(output.isPresent(), "Find by ID success");
        Assert.isTrue(output.get().getId() == 1, "ID is equal to 1");
    }
}
