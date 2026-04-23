package Lab11.App;

import Lab11.App.Base.Chef;
import Lab11.App.Base.Recipe;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        CookingContest contest = new CookingContest();

        // Add chefs using simple prototype names.
        contest.addSeniorChef("SC-1", 12);
        contest.addSeniorChef("SC-2", 7);

        SeniorChef seniorChef1 = (SeniorChef) contest.getChef("SC-1").orElseThrow();
        SeniorChef seniorChef2 = (SeniorChef) contest.getChef("SC-2").orElseThrow();

        contest.addJuniorChef("JC-1", seniorChef1);
        contest.addJuniorChef("JC-2", seniorChef2);

        JuniorChef juniorChef1 = (JuniorChef) contest.getChef("JC-1").orElseThrow();
        JuniorChef juniorChef2 = (JuniorChef) contest.getChef("JC-2").orElseThrow();

        // Add recipes and show the max-recipes limit in action.
        Recipe recipe1 = new Recipe(
                "Recipe 1",
                "Demo recipe for Senior Chef 1.",
                List.of("ingredient 1", "ingredient 2", "ingredient 3"),
                "1. Prep ingredients. 2. Cook everything. 3. Plate the dish."
        );
        Recipe recipe2 = new Recipe(
                "Recipe 2",
                "Second demo recipe for Senior Chef 1.",
                List.of("ingredient 4", "ingredient 5"),
                "1. Mix ingredients. 2. Finish and serve."
        );
        Recipe recipe3 = new Recipe(
                "Recipe 3",
                "Third demo recipe for Senior Chef 1.",
                List.of("ingredient 6", "ingredient 7", "ingredient 8"),
                "1. Combine. 2. Heat. 3. Serve."
        );
        Recipe recipe4 = new Recipe(
                "Recipe 4",
                "This one should exceed Senior Chef 1's recipe limit.",
                List.of("ingredient 9", "ingredient 10"),
                "1. Try to add it. 2. Observe the failure."
        );

        Recipe recipe5 = new Recipe(
                "Recipe 5",
                "First demo recipe for Senior Chef 2.",
                List.of("ingredient 11", "ingredient 12"),
                "1. Chop. 2. Cook. 3. Taste."
        );
        Recipe recipe6 = new Recipe(
                "Recipe 6",
                "Second demo recipe for Senior Chef 2.",
                List.of("ingredient 13", "ingredient 14", "ingredient 15"),
                "1. Prepare. 2. Bake. 3. Rest."
        );
        Recipe recipe7 = new Recipe(
                "Recipe 7",
                "Third demo recipe for Senior Chef 2.",
                List.of("ingredient 16", "ingredient 17"),
                "1. Simmer. 2. Finish."
        );

        Recipe juniorRecipe1 = new Recipe(
                "Recipe 8",
                "Junior Chef 1's only recipe.",
                List.of("ingredient 18", "ingredient 19"),
                "1. Simple prep. 2. Simple cook."
        );
        Recipe juniorRecipe2 = new Recipe(
                "Recipe 9",
                "Junior Chef 2's only recipe.",
                List.of("ingredient 20", "ingredient 21"),
                "1. Quick prep. 2. Quick cook."
        );

        printSection("ADDING RECIPES");
        printAddResult("SC-1", seniorChef1.addRecipe(recipe1));
        printAddResult("SC-1", seniorChef1.addRecipe(recipe2));
        printAddResult("SC-1", seniorChef1.addRecipe(recipe3));
        printAddResult("SC-1", seniorChef1.addRecipe(recipe4));

        printAddResult("SC-2", seniorChef2.addRecipe(recipe5));
        printAddResult("SC-2", seniorChef2.addRecipe(recipe6));
        printAddResult("SC-2", seniorChef2.addRecipe(recipe7));

        printAddResult("JC-1", juniorChef1.addRecipe(juniorRecipe1));
        printAddResult(
                "JC-1", juniorChef1.addRecipe(new Recipe(
                        "Recipe 10",
                        "This one should exceed Junior Chef 1's recipe limit.",
                        List.of("ingredient 22"),
                        "1. Confirm the limit."
                ))
        );

        printAddResult("JC-2", juniorChef2.addRecipe(juniorRecipe2));
        printAddResult(
                "JC-2", juniorChef2.addRecipe(new Recipe(
                        "Recipe 11",
                        "This one should exceed Junior Chef 2's recipe limit.",
                        List.of("ingredient 23"),
                        "1. Confirm the limit."
                ))
        );

        // Rate chefs and recipes.
        printSection("RATING CHEFS & RECIPES");
        seniorChef1.rate(8.0);
        seniorChef1.rate(9.0);
        seniorChef1.getRating().increment(1.0);
        seniorChef2.rate(7.5);
        seniorChef2.rate(8.0);

        juniorChef1.rate(6.0);
        juniorChef1.getRating().increment(0.5);
        juniorChef2.rate(6.5);

        recipe1.rate(9.0);
        recipe1.rate(9.5);
        recipe2.rate(8.0);
        recipe3.getRating().increment(7.5);
        recipe5.rate(7.0);
        recipe6.rate(8.5);
        juniorRecipe1.rate(6.5);
        juniorRecipe2.rate(7.2);

        // Print a simple prototype-style walkthrough of the contest state.
        printSection("COOKING CONTEST DEMO");
        printChefDetails("SC-1", seniorChef1);
        printChefDetails("SC-2", seniorChef2);
        printChefDetails("JC-1", juniorChef1);
        printChefDetails("JC-2", juniorChef2);

        printSection("RATINGS SUMMARY");
        printRatingDetails("SC-1 : rating", seniorChef1.getRating());
        printRatingDetails("SC-2 : rating", seniorChef2.getRating());
        printRatingDetails("JC-1 : rating", juniorChef1.getRating());
        printRatingDetails("JC-2 : rating", juniorChef2.getRating());
        printRatingDetails("RECIPE 1 : rating", recipe1.getRating());
        printRatingDetails("RECIPE 3 : rating", recipe3.getRating());
        printRatingDetails("RECIPE 8 : rating", juniorRecipe1.getRating());
    }

    private static void printSection(String title)
    {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("  " + title);
        System.out.println("═".repeat(70));
    }

    private static void printAddResult(String chefName, boolean added)
    {
        String status = added ? " Added" : " Rejected";
        System.out.printf("  %-20s → %s%n", chefName, status);
    }

    private static void printChefDetails(String label, Chef chef)
    {
        System.out.println("\n  ┌─ " + label);
        System.out.println("  │  Name:      " + chef.getName());
        System.out.println("  │  ID:        " + chef.getId());
        System.out.println("  │  Recipes:   " + chef.getRecipes().size() + " / " + chef.maxRecipes());

        if (chef instanceof SeniorChef seniorChef)
        {
            System.out.println("  │  Experience: " + seniorChef.getExperienceInYears() + " years");
        }
        else if (chef instanceof JuniorChef juniorChef)
        {
            System.out.println("  │  Supervisor: " + juniorChef.getSupervisor().getName());
        }

        if (!chef.getRecipes().isEmpty())
        {
            System.out.println("  │");
            List<Recipe> recipes = chef.getRecipes();
            for (int i = 0; i < recipes.size(); i++)
            {
                boolean isLast = i == recipes.size() - 1;
                String prefix = isLast ? "  └─" : "  ├─";
                Recipe recipe = recipes.get(i);
                System.out.println(prefix + " " + recipe.getName() + " | Rating: " + recipe.getRating().latest());
            }
        }
        else
        {
            System.out.println("  └─ (no recipes)");
        }
    }

    private static void printRatingDetails(String label, Lab11.App.Base.Rating rating)
    {
        System.out.println("\n> " + label + " : " + rating.getFormatted());
        System.out.printf("     Latest:     %.2f%n", rating.latest());
        System.out.printf("     Average:    %.2f%n", rating.average());
        System.out.printf("     Count:      %d%n", rating.getRatingCount());
    }
}
