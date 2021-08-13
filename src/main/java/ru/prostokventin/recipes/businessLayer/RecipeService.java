package ru.prostokventin.recipes.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.prostokventin.recipes.persistance.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public long saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe).getId();
    };

    public Optional<Recipe> findRecipeById(long id) {
        return recipeRepository.findRecipeById(id);
    }

    public void deleteRecipeById(long id) {
        recipeRepository.deleteById(id);
    }

    public List<Recipe> searchRecipesByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> searchRecipesByName(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public List<Recipe> findAllRecipies() {
        return recipeRepository.findAll();
    }

}
