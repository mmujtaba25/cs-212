package Lab11.App;

import Lab11.App.Base.Chef;

public class JuniorChef extends Chef
{
    private final SeniorChef supervisor;

    public JuniorChef(String name, SeniorChef supervisor)
    {
        super(name);
        this.supervisor = supervisor;
    }

    @Override
    public int maxRecipes() { return 1; }

    /* GETTERS */

    public SeniorChef getSupervisor() { return supervisor; }
}
