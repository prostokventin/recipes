package ru.prostokventin.recipes.persistance;

import org.springframework.data.repository.CrudRepository;
import ru.prostokventin.recipes.businessLayer.*;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    Optional<Recipe> findRecipeById(long id);
    void deleteById(long id);
    Recipe save(Recipe recipe);
    List<Recipe> findAllByCategoryIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findAllByNameContainingIgnoreCaseOrderByDateDesc(String category);
    List<Recipe> findAll();
}
