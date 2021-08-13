package ru.prostokventin.recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostokventin.recipes.persistance.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
