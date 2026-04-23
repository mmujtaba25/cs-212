package Lab11.App;

import Lab11.App.Base.Chef;

public class SeniorChef extends Chef
{
    private final int experienceInYears;

    public SeniorChef(String name, int experienceInYears)
    {
        super(name);
        this.experienceInYears = experienceInYears;
    }

    @Override
    public int maxRecipes() { return 3; }

    /* RATING */

    @Override
    public void rate(double newRating)
    {
        // rate based on experience
        double adjustedRating = newRating * (1 + experienceInYears / 10.0); // more experience = higher rating
        super.rate(adjustedRating);
    }

    /* GETTERS */

    public int getExperienceInYears() { return experienceInYears; }
}
