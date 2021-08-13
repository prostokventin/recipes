package ru.prostokventin.recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.prostokventin.recipes.businessLayer.UserService;
import ru.prostokventin.recipes.businessLayer.User;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(newUser);
    }

}
