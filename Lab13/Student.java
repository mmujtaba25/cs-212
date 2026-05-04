package Lab13;

public record Student(String name, double marks)
{
    public static final String SEPARATOR = "-";

    public Student(String name, double marks)
    {
        // only numbers and alphabets with spaces
        this.name = name.replaceAll("[^a-zA-Z0-9 ]", "");
        this.marks = marks;
    }

    public static Student from(String name, double marks) { return new Student(name, marks); }

    public static Student from(String name, String marksStr) throws NumberFormatException
    {
        double marks = Double.parseDouble(marksStr.trim());
        return new Student(name, marks);
    }

    public String stringify() { return name + " " + SEPARATOR + " " + marks; }
}
