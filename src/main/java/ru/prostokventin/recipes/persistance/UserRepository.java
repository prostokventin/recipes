package ru.prostokventin.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import ru.prostokventin.recipes.businessLayer.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    User save(User user);

}
