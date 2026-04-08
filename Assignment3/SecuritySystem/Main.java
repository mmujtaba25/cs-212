package Assignment3.SecuritySystem;

public class Main
{
    public static void main(String[] args)
    {
        SmartAppliance myCoffeeMaker = new SmartAppliance("Espresso Machine");
        SmartHome home = new SmartHome();
        home.addAppliance(myCoffeeMaker);
        myCoffeeMaker.powerOn();

        home = null; // putting home = null ie deleting it
        // turning coffee maker power on again

        myCoffeeMaker.powerOn();
    }
}
