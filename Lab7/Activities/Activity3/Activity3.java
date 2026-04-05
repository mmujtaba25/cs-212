package Lab7.Activities.Activity3;

public class Activity3
{
    public static void main(String[] args)
    {
        B b = new B();
    }
}

class A
{
    public A(int x) {}
}

class B extends A
{
    /** Reason:
     * In java every child class is required to call the constructor of parent class (super)
     * the call to super is not required to be explicit if super constructor is no args
     * is super requires arguments, java doesn't know what to pass as arguments
     * therefore, we need to explicity tell java, to call with the given args
     */
    // public B() {}

    /**
     * Solution: We can either pass 0 to the `x` argument of super (constructor of parent)
     * <br>
     * <br>
     * {@code public B() {super(0);}}
     * <br>
     * <br>
     * Or take value of x from the user, and then pass it
     * <br>
     * {@code public B(int x) {super(x);}}
     */
    public B() {super(0);}
}
