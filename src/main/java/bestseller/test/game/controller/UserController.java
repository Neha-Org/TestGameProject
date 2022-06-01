package bestseller.test.game.controller;

import bestseller.test.game.entity.User;
import bestseller.test.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user found");
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (users.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users found");
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

}
