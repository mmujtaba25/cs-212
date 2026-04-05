package Lab8.Hospital;

import java.util.ArrayList;

public class Department
{
    private String name;
    private final ArrayList<StaffMember> staffMembers = new ArrayList<>();
    private final ArrayList<Patient> patients = new ArrayList<>();

    public Department(String name)
    {
        this.name = name;
    }

    /* ADDING & REMOVING */

    public StaffMember addStaff(StaffMember staff)
    {
        staffMembers.add(staff);
        return staff;
    }

    public Patient addPatient(Patient patient)
    {
        patients.add(patient);
        return patient;
    }

    public boolean removeStaff(StaffMember staff) { return staffMembers.remove(staff); }

    public boolean removePatient(Patient patient) { return patients.remove(patient); }

    /* GETTERS */

    public String getName() { return name; }

    public ArrayList<StaffMember> getStaffMembers() { return staffMembers; }

    public ArrayList<Patient> getPatients() { return patients; }

    /* SETTERS */

    public Department setName(String name)
    {
        this.name = name;
        return this;
    }
}
