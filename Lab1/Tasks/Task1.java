package Lab1.Tasks;

import java.util.Scanner;

public class Task1
{
    public static void main(String[] args)
    {
        Student student;
        String name;
        int year;
        int numberOfCourses;
        float gpa;

        Scanner scanner = new Scanner(System.in);
        name = getFromInput(scanner, "Enter student name: ");
        year = Integer.parseInt(getFromInput(scanner, "Enter student year (1-4): "));
        numberOfCourses = Integer.parseInt(getFromInput(scanner, "Enter number of courses: "));
        gpa = Float.parseFloat(getFromInput(scanner, "Enter GPA: "));
        scanner.close();

        student = Student.from(name, year, numberOfCourses, gpa);

        student.printInfo();
    }

    private static String getFromInput(Scanner scanner, String message)
    {
        System.out.println(message);
        return scanner.nextLine();
    }

    private record Student(String name, StudentYear year, int numberOfCourses, float gpa)
    {
        public void printInfo()
        {
            System.out.println("Student Name: " + name);
            System.out.println("Year: " + year.getValue());
            System.out.println("Title: " + year.getTitle());
            System.out.println("Number of Courses: " + numberOfCourses);
            System.out.println("GPA: " + gpa);
        }

        /* Statics */

        public static Student from(String name, StudentYear year, int numberOfCourses, float gpa)
        {
            return new Student(name, year, numberOfCourses, gpa);
        }

        public static Student from(String name, int year, int numberOfCourses, float gpa)
        {
            return from(name, StudentYear.fromYear(year), numberOfCourses, gpa);
        }
    }

    private enum StudentYear
    {
        NOT_ASSIGNED(0),
        FRESHMAN(1),
        SOPHOMORE(2),
        JUNIOR(3),
        SENIOR(4),
        GRADUATED(5);

        private final int value;

        StudentYear(int year)
        {
            this.value = year;
        }

        public int getValue()
        {
            return value;
        }

        public String getTitle()
        {
            return switch (this)
            {
                case NOT_ASSIGNED -> "Not Assigned";
                case FRESHMAN -> "Freshman";
                case SOPHOMORE -> "Sophomore";
                case JUNIOR -> "Junior";
                case SENIOR -> "Senior";
                case GRADUATED -> "Graduated";
            };
        }

        public static StudentYear fromYear(int year)
        {
            return switch (year)
            {
                case 1 -> FRESHMAN;
                case 2 -> SOPHOMORE;
                case 3 -> JUNIOR;
                case 4 -> SENIOR;
                default -> year >= 5 ? GRADUATED : NOT_ASSIGNED;
            };
        }
    }
}
