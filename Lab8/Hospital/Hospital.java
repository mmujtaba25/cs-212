package Lab8.Hospital;

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
