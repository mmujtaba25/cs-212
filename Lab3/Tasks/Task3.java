package Lab3.Tasks;

import java.util.ArrayList;
import java.util.Scanner;

public class Task3
{
    public static void main(String[] args)
    {
        ArrayList<Student> students = new ArrayList<Student>();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter -1 stop entering student data.\n");
        while (true)
        {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            if (name.equals("-1"))
                break;
            int marks;
            do
            {
                // catch invalid input such as letters
                try
                {
                    System.out.print("Enter marks obtained: ");
                    marks = scanner.nextInt();
                }
                catch (Exception e)
                {
                    System.out.println("Invalid marks entered. Please try again.");
                    scanner.next(); // clear invalid input
                    continue;
                }

                if (marks < -1 || marks > 100)
                    System.out.println("Invalid marks entered. Please try again.");
                else
                    break;
            } while (true);

            if (marks == -1)
                break;

            scanner.nextLine(); // consume newline to allow next string input

            students.add(Student.from(name, marks));
        }

        int total = calculateTotalMarks(students);
        float average = calculateAverageMarks(students);
        int highest = getHighest(students);
        int lowest = getLowest(students);
        int numAboveAverage = studentsAboveAverage(students, average);

        System.out.println("\n\n>-- STUDENT MARKS DETAILS -- <\n");
        System.out.println("Students Count: " + students.size() + "\n");
        for (Student student : students)
            System.out.println(student.getPrintableLine());

        System.out.println("\n\n>-- STUDENT MARKS STATISTICS --<\n");
        System.out.println("Students Count: " + students.size() + "\n");

        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.println("Number of Students Above Average: " + numAboveAverage);
    }

    private static int calculateTotalMarks(ArrayList<Student> students)
    {
        int total = 0;
        for (Student student : students)
        {
            total += student.marks();
        }
        return total;
    }

    private static float calculateAverageMarks(ArrayList<Student> students)
    {
        int total = calculateTotalMarks(students);
        return (float) total / students.size();
    }

    private static int getHighest(ArrayList<Student> students)
    {
        int highest = Integer.MIN_VALUE;
        for (Student student : students)
        {
            if (student.marks() > highest)
            {
                highest = student.marks();
            }
        }
        return highest;
    }

    private static int getLowest(ArrayList<Student> students)
    {
        int lowest = Integer.MAX_VALUE;
        for (Student student : students)
        {
            if (student.marks() < lowest)
            {
                lowest = student.marks();
            }
        }
        return lowest;
    }

    private static int studentsAboveAverage(ArrayList<Student> students, float average)
    {
        int count = 0;
        for (Student student : students)
        {
            if (student.marks() > average)
            {
                count++;
            }
        }
        return count;
    }

    private record Student(String name, int marks)
    {
        public static Student from(String name, int marks)
        {
            return new Student(name, marks);
        }

        public String getPrintableLine()
        {
            return "Name: " + name.strip() + ", Marks: " + marks;
        }
    }
}
