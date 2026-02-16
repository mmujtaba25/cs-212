package Lab5.Tasks;

public class Task1
{
    public static void main(String[] args)
    {
        Student ali = new Student("Ali", 20, 70);
        Student hamza = new Student("Hamza", 25, 60);
        Student sheharyar = new Student("Sheharyar", 18, 80);

        System.out.println("------------------------");
        ali.printDetails();
        System.out.println("------------------------");
        hamza.printDetails();
        System.out.println("------------------------");
        sheharyar.printDetails();
        System.out.println("------------------------");
    }

    private static class Student
    {
        private final String name;
        private final int age;
        private final int marks;

        public Student(String name, int age, int marks)
        {
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        public void printDetails()
        {
            System.out.println("Name : " + name);
            System.out.println("Age  : " + age);
            System.out.println("Marks: " + marks);
        }
    }
}
