package Lab13;

import java.io.*;
import java.util.*;

public class StudentRecords implements AutoCloseable
{
    private final String fileName;
    private final BufferedWriter writer;

    public StudentRecords(String fileName) throws IOException
    {
        this.fileName = fileName;
        this.writer = new BufferedWriter(new FileWriter(fileName, true));
    }

    @Override
    public void close() throws IOException
    {
        writer.close();
    }

    public void appendRecord(String name, double marks) throws IOException
    {
        Student student = Student.from(name, marks);
        writer.write(student.stringify());
        writer.newLine();
        writer.flush();
    }

    public List<Student> getAllRecords() throws IOException
    {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(Student.SEPARATOR);
                if (parts.length >= 2)
                {
                    try { students.add(Student.from(parts[0], parts[1])); }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Skipping invalid line: " + line);
                    }
                }
            }
        }
        return students;
    }

    public List<Student> searchByName(String name) throws IOException
    {
        List<Student> students = getAllRecords();
        List<Student> results = new ArrayList<>();
        for (Student s : students)
            if (s.name().toLowerCase().contains(name.toLowerCase())) results.add(s);
        return results;
    }

    public int countRecords() throws IOException
    {
        return getAllRecords().size();
    }

    public OptionalDouble averageMarks() throws IOException
    {
        return getAllRecords().stream().mapToDouble(Student::marks).average();
    }

    public Optional<Student> highestMarks() throws IOException
    {
        return getAllRecords().stream().max(Comparator.comparingDouble(Student::marks));
    }

    public Optional<Student> lowestMarks() throws IOException
    {
        return getAllRecords().stream().min(Comparator.comparingDouble(Student::marks));
    }
}
