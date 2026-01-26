package Lab1.Activities;

// Main
public class Activity6
{
    public static void main(String[] args)
    {
        System.out.print("Hello World!");
    }
}

// 1) Naming your file incorrectly, then compiling.
// public class ActivitySix
// {
//     public static void main(String[] args)
//     {
//         System.out.print("Hello World!");
//     }
// }
// -----------------------------------------------------------------------
// Activity6.java:3: error: class ActivitySix is public, should be declared in a file named ActivitySix.java
// public class ActivitySix
//        ^
// 1 error

// 2) Forgetting a keyword such as void or class
// public class Activity6
// {
//     public static main(String[] args)
//     {
//         System.out.print("Hello World!");
//     }
// }
// -----------------------------------------------------------------------
// Activity6.java:5: error: invalid method declaration; return type required
//     public static main(String[] args)
//                   ^
// 1 error

// 3) Forgetting a quotation mark "
// public class Activity6
// {
//     public static void main(String[] args)
//     {
//         System.out.print("Hello World!);
//     }
// }
// -----------------------------------------------------------------------
// Activity6.java:7: error: unclosed string literal
//         System.out.print("Hello World!);
//                          ^
// 1 error

// 4) Forgetting a parenthesis ( or )
// public class Activity6
// {
//     public static void main(String[] args)
//     {
//         System.out.print("Hello World!";
//     }
// }
// -----------------------------------------------------------------------
// Activity6.java:7: error: ')' or ',' expected
//         System.out.print("Hello World!";
//                                        ^
// 1 error

// 5) Forgetting a dot .
// public class Activity6
// {
//     public static void main(String[] args)
//     {
//         System.out.print("Hello World!");
//     }
// }
// -----------------------------------------------------------------------
// Activity6.java:7: error: cannot find symbol
//         Systemout.print("Hello World!");
//         ^
//   symbol:   variable Systemout
//   location: class Activity6
// 1 error

// 6) Using too many or too few braces { or }
// public class Activity6
// {
//     public static void main(String[] args)
//     {
//         System.out.print("Hello World!");
//     }
// }
// }
// -----------------------------------------------------------------------
// Activity6.java:10: error: class, interface, enum, or record expected
// }
// ^
// 1 error
