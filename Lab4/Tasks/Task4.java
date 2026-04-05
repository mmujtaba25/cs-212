package Lab4.Tasks;

import java.util.Scanner;

public class Task4
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        Student[] students = new Student[10];

        for (int i = 0; i < students.length; i++)
        {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            int marks = getMarks(scanner);

            scanner.nextLine(); // consume newline to allow next string input

            students[i] = Student.from(name, marks);
        }

        printStats(students);
    }

    private static void printStats(Student[] students)
    {
        int total = calculateTotalMarks(students);
        float average = calculateAverageMarks(students);
        int highest = getHighest(students);
        int lowest = getLowest(students);
        int numAboveAverage = studentsAboveAverage(students, average);

        System.out.println("\n\n>-- STUDENT MARKS DETAILS -- <\n");
        System.out.println("Students Count: " + students.length + "\n");
        for (Student student : students)
            System.out.println(student.getPrintableLine());

        System.out.println("\n\n>-- STUDENT MARKS STATISTICS --<\n");
        System.out.println("Students Count: " + students.length + "\n");

        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);
        System.out.println("Number of Students Above Average: " + numAboveAverage);
    }

    /* INPUTS */

    private static int getMarks(Scanner scanner)
    {
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
        return marks;
    }

    /* FUNCTIONS */

    private static int calculateTotalMarks(Student[] students)
    {
        int total = 0;
        for (Student student : students)
        {
            total += student.marks();
        }
        return total;
    }

    private static float calculateAverageMarks(Student[] students)
    {
        int total = calculateTotalMarks(students);
        return (float) total / students.length;
    }

    private static int getHighest(Student[] students)
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

    private static int getLowest(Student[] students)
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

    private static int studentsAboveAverage(Student[] students, float average)
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

    /* PRIVATE CLASSES */

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
