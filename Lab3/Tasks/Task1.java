package Lab3.Tasks;

import java.util.Scanner;

public class Task1
{
    public static void main(String[] args)
    {
        Subject[] subjects = { //
                new Subject("CS-101", 100),   // focp
                new Subject("CS-212", 100),   // oop
                new Subject("MATH-101", 100), // calculus
                new Subject("MATH-121", 100), // linear algebra
                new Subject("MATH-161", 100)  // discrete math
        };

        String studentName;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        studentName = scanner.nextLine();
        for (final Subject subject : subjects)
        {
            int marks;
            do
            {
                System.out.printf(
                        "Enter marks obtained in %s (Maximum: %d): ",
                        subject.name,
                        subject.totalMarks()
                );
                marks = scanner.nextInt();
                // validation
                if (!subject.setObtainedMarks(marks))
                    System.out.println("Invalid marks entered. Please try again.");
                else
                    break;
            } while (true);
        }
        scanner.close();

        boolean isEligibleForScholarship = isEligibleForScholarship(subjects);

        System.out.print("\n\n>-- RESULT --<\n\n");

        System.out.println("Student Name: " + studentName);
        System.out.println("Scholarship Eligibility: " + (isEligibleForScholarship
                                                          ? "Eligible"
                                                          : "Not Eligible"));
        System.out.println("Subject Details:");
        for (final Subject subject : subjects)
        {
            System.out.println(subject.getPrintableLine());
        }
    }

    /// Is Eligible when all subjects have grade B+ or better
    private static boolean isEligibleForScholarship(Subject[] subjects)
    {
        boolean hasWorseThanBPlus = false;
        for (final Subject subject : subjects)
        {
            if (subject.getGrade().worseThan(Grade.B_PLUS))
            {
                hasWorseThanBPlus = true;
                break;
            }
        }

        if (hasWorseThanBPlus)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private static final class Subject
    {
        private final int PASSING_PERCENTAGE = 40;

        private final String name;
        private final int totalMarks;

        private int obtainedMarks;

        private Subject(String name, int totalMarks)
        {
            this.name = name;
            this.totalMarks = totalMarks;
        }

        public boolean setObtainedMarks(int marks)
        {
            if (marks < 0 || marks > totalMarks)
            {
                return false;
            }
            this.obtainedMarks = marks;
            return true;
        }

        public float calculatePercentage()
        {
            return ((float) obtainedMarks / totalMarks) * 100;
        }

        public boolean hasPassed()
        {
            return calculatePercentage() >= PASSING_PERCENTAGE;
        }

        public Grade getGrade()
        {
            return Grade.A.fromPercentage(calculatePercentage());
        }

        public String getPrintableLine()
        {
            return String.format(
                    "%s: %d/%d (%.2f%%) - Grade: %s - %s", //
                    name, //
                    obtainedMarks, //
                    totalMarks, //
                    calculatePercentage(), //
                    getGrade().getName(), //
                    hasPassed() ? "Passed" : "Failed" //
            );
        }

        /* GETTERS */

        public String name() { return name; }

        public int obtainedMarks() { return obtainedMarks; }

        public int totalMarks() { return totalMarks; }
    }

    private enum Grade
    {
        A(80f),
        B_PLUS(70f),
        B(60f),
        C_PLUS(50f),
        C(40f),
        F(-1f);

        private final float percentage;

        Grade(float percentage)
        {
            this.percentage = percentage;
        }

        public String getName()
        {
            return switch (this)
            {
                case A -> "A";
                case B_PLUS -> "B+";
                case B -> "B";
                case C_PLUS -> "C+";
                case C -> "C";
                case F -> "F";
            };
        }

        public Grade fromPercentage(float percentage)
        {
            for (Grade grade : Grade.values())
            {
                if (percentage >= grade.getPercentage())
                {
                    return grade;
                }
            }
            return F;
        }

        public boolean betterThan(Grade other)
        {
            return this.percentage > other.percentage;
        }

        public boolean worseThan(Grade other)
        {
            return this.percentage < other.percentage;
        }

        public float getPercentage()
        {
            return percentage;
        }
    }
}
