package ru.prostokventin.recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.prostokventin.recipes.businessLayer.Recipe;
import ru.prostokventin.recipes.businessLayer.RecipeService;
import ru.prostokventin.recipes.businessLayer.User;
import ru.prostokventin.recipes.businessLayer.UserService;
import ru.prostokventin.recipes.security.IAuthenticationFacade;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
public class RecipeController {

    private final String SEARCH_MORE_THAN_ONE_PARAMETER_ERROR = "More than one parameter was defined";
    private final String SEARCH_EMPTY_PARAMETER_ERROR = "One of the 'category' or 'name' parameter must be defined";

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @PostMapping("/api/recipe/new")
    public @ResponseBody
    ResponseEntity<?> addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe newRecipe = new Recipe();
        newRecipe.setName(recipe.getName());
        newRecipe.setCategory(recipe.getCategory());
        newRecipe.setDescription(recipe.getDescription());
        newRecipe.setIngredients(recipe.getIngredients());
        newRecipe.setDirections(recipe.getDirections());
        newRecipe.setUser(getAuthenticatedUser());
        return ResponseEntity.ok(Map.of("id", recipeService.saveRecipe(newRecipe)));
    }

    @GetMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody Recipe getRecipe(@PathVariable long id) {
        Optional<Recipe> optRecipe = recipeService.findRecipeById(id);
        return optRecipe.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    @DeleteMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public @ResponseBody void deleteRecipe(@PathVariable long id) {
        Optional<Recipe> optRecipe = recipeService.findRecipeById(id);
        Recipe recipe = optRecipe.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        if (!Objects.equals(getAuthenticatedUser(), recipe.getUser())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        recipeService.deleteRecipeById(id);
    }

    @PutMapping("/api/recipe/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public @ResponseBody void updateRecipe(
            @PathVariable long id,
            @Valid @RequestBody Recipe recipe
    ) {
        Optional<Recipe> optRecipe = recipeService.findRecipeById(id);
        Recipe foundRecipe = optRecipe.orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        User authenticatedUser = getAuthenticatedUser();
        if (!Objects.equals(authenticatedUser, foundRecipe.getUser())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        Recipe updatedRecipe = new Recipe();
        updatedRecipe.setId(foundRecipe.getId());
        updatedRecipe.setName(recipe.getName());
        updatedRecipe.setCategory(recipe.getCategory());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setIngredients(recipe.getIngredients());
        updatedRecipe.setDirections(recipe.getDirections());
        updatedRecipe.setUser(authenticatedUser);
        recipeService.saveRecipe(updatedRecipe);
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> searchRecipes(
            @RequestParam(value = "category") Optional<String> category,
            @RequestParam(value = "name") Optional<String> name
    ) {
        if (category.isPresent() && name.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SEARCH_MORE_THAN_ONE_PARAMETER_ERROR);
        }
        if (category.isEmpty() && name.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, SEARCH_EMPTY_PARAMETER_ERROR);
        }
        if (category.isPresent()) {
            return recipeService.searchRecipesByCategory(category.get());
        }
        if (name.isPresent()) {
            return recipeService.searchRecipesByName(name.get());
        }
        return List.of();
    }

    private User getAuthenticatedUser() {
        String email = authenticationFacade.getAuthentication().getName();
        User authenticatedUser = userService.findByEmail(email).get();
        return authenticatedUser;
    }

}
