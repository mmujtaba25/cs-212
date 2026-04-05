# Activity 3 output

```java
class Teacher {
    int teacherId;
    String subject;

    public Teacher(int teacherId, String subject) {
        this.teacherId = teacherId;
        this.subject = subject;
    }
}

public class Main {
    public static void main(String[] args) {
        Teacher t1 = new Teacher(501, "Mathematics");
        System.out.println(t1.teacherId);
        System.out.println(t1.subject);
    }
}
```

## OUTPUT:
```shell
501
Mathematics
```