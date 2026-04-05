package Lab7.Activities.Activity2;

public class Activity2
{
    public static void main(String[] args)
    {
        B b = new B(3);
    }
}

class A
{
    public A()
    {
        System.out.println("A's no-arg constructor is invoked");
    }
}

class B extends A
{
    public B(int t)
    {
        System.out.println("B's constructor is invoked");
    }
}

// OUTPUT:
// A's no-arg constructor is invoked
// B's constructor is invoked
//
