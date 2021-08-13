package ru.prostokventin.recipes.businessLayer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Entity
//lombook doesn't work with tests
//@Data
//@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue
    @JsonIgnore
    private long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "category")
    @NotBlank
    private String category;

    @Column(name = "date")
    private final LocalDateTime date = LocalDateTime.now();

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "ingredients")
    @Size(min = 1)
    @NotEmpty
    private String[] ingredients;

    @Column(name = "directions")
    @Size(min = 1)
    @NotEmpty
    private String[] directions;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Recipe() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getDirections() {
        return directions;
    }

    public void setDirections(String[] directions) {
        this.directions = directions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
