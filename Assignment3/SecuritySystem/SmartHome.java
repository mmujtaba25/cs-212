package Assignment3.SecuritySystem;

import java.util.ArrayList;

public class SmartHome
{
    private SecuritySystem sSystem;
    private final ArrayList<SmartAppliance> appliances;

    // no-arg constructor
    public SmartHome()
    {
        // initializing new security system
        this.sSystem = new SecuritySystem();
        this.appliances = new ArrayList<>();
    }

    public void addAppliance(SmartAppliance appliance)
    {
        appliances.add(appliance);
    } // method for adding appliances to home

    public void removeAppliance(SmartAppliance appliance)
    {
        appliances.remove(appliance);
    } // method for removing appliances from home
}
