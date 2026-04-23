package Lab11.App.Base;

import java.util.List;
import java.util.Objects;

public class Recipe implements Rateable
{
    private final String name;
    private final String description;
    private final List<String> ingredients;
    private final String instructions;

    private final Rating rating = new Rating(0);

    public Recipe(String name, String description, List<String> ingredients, String instructions)
    {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    @Override
    public void rate(double newRating) { rating.rate(newRating); }

    /* GETTERS & SETTERS */

    public String getName() { return name; }

    public String getDescription() { return description; }

    public List<String> getIngredients() { return ingredients; }

    public String getInstructions() { return instructions; }

    @Override
    public Rating getRating() { return rating; }

    /* OBJECT OVERRIDE */

    @Override
    public boolean equals(Object obj)
    {
        // same if all fields are same
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Recipe recipe = (Recipe) obj;
        return name.equals(recipe.name) &&                  //
                description.equals(recipe.description) &&   //
                ingredients.equals(recipe.ingredients) &&   //
                instructions.equals(recipe.instructions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, description, ingredients, instructions);
    }

    @Override
    public String toString()
    {
        return String.format("Recipe[name='%s', rating=%.1f]", name, rating.latest());
    }
}
