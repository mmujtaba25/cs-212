package Lab2.Activities;

public class Activity5
{ // 1: missing opening bracket
    public static void main(String[] args) // 2: missing void - 3: missing []
    {
        System.out.println("Hello world"); // 4: missing quotes
        System.out.println("Do you like this program?"); // 5: "s"ystem - 6: "P"ri""tln - 7: missing quotes
        System.out.println(); // 8: missing ;

        System.out.println("I wrote it myself."); // 9: System.out - 10: missing ";"
    } // 11: "{" used instead of "}"
}

/*
Solution blueprint:

```java
public class Activity5 #
    public static #### main(String## args) {
        System.out.println(#Hello world#);
        |s|ystem.out.|P|ri#tnln("Do you like this program|"|?#);
        System.out.println()#

        System####.println("I wrote it myself."#;
    |{|#
}
```

#  -> missing character
|| -> remove char in between
*/
