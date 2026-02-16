# Activity 2 output

```java
class Book {
    String title;
    double price;

    public Book(String title, double price) {
        this.title = title;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("Java Basics", 29.99);
        System.out.println(b1.title);
        System.out.println(b1.price);
    }
}

```

OUTPUT:
```shell
Java Basics
29.99
```