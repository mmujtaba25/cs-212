package Lab11.App.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Chef implements Rateable
{
    private static long _lastID = 0;
    private final long id = _lastID++;

    private final String name;
    private final List<Recipe> recipes = new ArrayList<>();

    private final Rating rating = new Rating(0);

    public abstract int maxRecipes();

    public Chef(String name)
    {
        this.name = name;
    }

    /* RECIPE */

    public boolean addRecipe(Recipe recipe)
    {
        if (recipes.size() >= maxRecipes()) return false;

        recipes.add(recipe);
        return true;
    }

    /* RATEABLE */

    @Override
    public void rate(double newRating) { rating.rate(newRating); }

    @Override
    public Rating getRating() { return rating; }

    /* GETTERS & SETTERS */

    public long getId() { return id; }

    public String getName() { return name; }

    public List<Recipe> getRecipes() { return recipes; }

    /* OBJECT OVERRIDE */

    @Override
    public boolean equals(Object obj)
    {
        // same if id is same
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Chef chef = (Chef) obj;
        return id == chef.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override
    public String toString()
    {
        return String.format(
                "%s[id=%d, name='%s', recipes=%d, rating=%.1f]",
                this.getClass().getSimpleName(),
                id,
                name,
                recipes.size(),
                rating.latest()
        );
    }
}
