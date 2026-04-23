package Lab11.App;

import Lab11.App.Base.Chef;
import Lab11.App.Base.Recipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookingContest
{
    private final List<Chef> participants = new ArrayList<>();

    /* CHEFS */

    public void addSeniorChef(String name, int experienceInYears)
    {
        participants.add(new SeniorChef(name, experienceInYears));
    }

    public void addJuniorChef(String name, SeniorChef supervisor)
    {
        participants.add(new JuniorChef(name, supervisor));
    }

    public Optional<Chef> getChef(String name)
    {
        return participants.stream().filter(chef -> chef.getName().equals(name)).findFirst();
    }
}
