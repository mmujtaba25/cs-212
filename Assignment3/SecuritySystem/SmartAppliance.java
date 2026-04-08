package Assignment3.SecuritySystem;

public class SmartAppliance
{
    private final String name;

    // 1-arg constructor
    public SmartAppliance(String name)
    {
        this.name = name;
    }

    public void powerOn()
    {
        System.out.println(name + " is running.");
    }
}
