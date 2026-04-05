package Lab8.Hospital;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Hospital
{
    private String name;
    private String address;
    private final ArrayList<Department> departments = new ArrayList<>();

    public Hospital(String name, String address)
    {
        this.name = name;
        this.address = address;
    }
    
    public void printInfo()
    {
        System.out.println("Hospital: " + this.getName() + ", Address: " + this.getAddress());
        System.out.println("Departments:");
        for (Department d : this.getDepartments())
        {
            System.out.println(d.getName());
        }
    }

    /* ADDING */

    public Department addDepartment(Department department)
    {
        departments.add(department);
        return department;
    }

    public StaffMember addStaff(StaffMember staff, Department department) { return department.addStaff(staff); }

    public Patient addPatient(Patient patient, Department department) { return department.addPatient(patient); }

    /* REMOVING */

    public boolean removeDepartment(Department department) { return departments.remove(department); }

    public boolean removeStaff(StaffMember staff)
    {
        for (Department department : departments)
            if (department.removeStaff(staff)) return true;

        return false;
    }

    public boolean removePatient(Patient patient)
    {
        for (Department department : departments)
            if (department.removePatient(patient)) return true;

        return false;
    }

    /* GETTERS */

    public String getName() { return name; }

    public String getAddress() { return address; }

    public ArrayList<Department> getDepartments() { return departments; }

    public @Nullable Department getDepartmentByName(String name)
    {
        return departments.stream().filter(department -> department.getName().equals(name)).findFirst().orElse(null);
    }

    /* SETTERS */

    public Hospital setName(String name)
    {
        this.name = name;
        return this;
    }

    public Hospital setAddress(String address)
    {
        this.address = address;
        return this;
    }
}
