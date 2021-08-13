package ru.prostokventin.recipes.businessLayer;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
//lombook doesn't work with tests
//@Data
//@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "email")
    @NotBlank
    @Pattern(regexp = "\\w+@\\w+\\.\\w+")
    private String email;

    @Column(name = "password")
    @NotBlank
    @Size(min = 8)
    private String password;

    @Column(name = "roles")
    private final String roles = "ROLE_USER";

    @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(recipes, user.recipes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, roles, recipes);
    }
}
