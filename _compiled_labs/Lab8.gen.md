# Lab 8

## Main

```java
package Lab8.Hospital;

import Lab8.Hospital.Doctors.Intern;
import Lab8.Hospital.Doctors.SeniorDoctor;
import Lab8.Hospital.Doctors.Surgeon;

import java.util.Date;

public class Main
{
    public static void main(String[] args)
    {
        // Hospital
        Hospital hospital = new Hospital("City Hospital", "123 Main St");
        Department surgery = new Department("Surgery");
        Department pediatrics = new Department("Pediatrics");
        hospital.addDepartment(surgery);
        hospital.addDepartment(pediatrics);

        // Doctors
        Date now = new Date();
        SeniorDoctor drSmithSrDoc = new SeniorDoctor("Dr. Smith", Gender.MALE, now, 40);
        Surgeon drLeeSurgeon = new Surgeon("Dr. Lee", Gender.FEMALE, now, 45);
        Intern johnIntern = new Intern("Dr. John", Gender.MALE, now, 30).setSupervisor(drSmithSrDoc);

        // Add staff
        hospital.addStaff(johnIntern, surgery);
        hospital.addStaff(drSmithSrDoc, surgery);
        hospital.addStaff(drLeeSurgeon, surgery);

        // Patient
        Patient johnPatient = new Patient("John Doe", now, Gender.MALE).setDateAccepted(new Date())
                .setDaysStayed(5)
                .setTreatingDoctor(drSmithSrDoc)
                .setDiagnosisReport("Flu");

        // Add patient to pediatrics (as example)
        hospital.addPatient(johnPatient, pediatrics);

        // Treat
        johnIntern.treatPatient();
        drSmithSrDoc.treatPatient();
        drLeeSurgeon.treatPatient();


        johnPatient.printInfo();
        hospital.printInfo();
    }
}
```

## Hospital

```java
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

    /// Returns {@code null} if no department with the given name is found
    public Department getDepartmentByName(String name)
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
```

## Department

```java
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
```

## StaffMember

```java
package Lab8.Hospital;

import java.util.Date;

public abstract class StaffMember
{
    // start from -1 since the first staff member will have ID 0
    private static long lastID = -1;

    private final long id;
    private final String name;
    private final Gender gender;
    private final Date joinedDate;
    private int workingHours;

    public StaffMember(String name, Gender gender, Date joinedDate, int workingHours)
    {
        this.id = ++lastID;
        this.name = name;
        this.gender = gender;
        this.joinedDate = joinedDate;
        this.workingHours = workingHours;
    }

    /* GETTERS */

    public long getId() { return id; }

    public String getName() { return name; }

    public Gender getGender() { return gender; }

    public Date getJoinedDate() { return joinedDate; }

    public int getWorkingHours() { return workingHours; }

    /* SETTERS */

    public StaffMember setWorkingHours(int workingHours)
    {
        this.workingHours = workingHours;
        return this;
    }
}
```

## Doctor

```java
package Lab8.Hospital.Doctors;

import Lab8.Hospital.Gender;
import Lab8.Hospital.StaffMember;

import java.util.Date;

public abstract class Doctor extends StaffMember
{
    private String speciality;

    public Doctor(String name, Gender gender, Date joinedDate, int workingHours)
    {
        super(name, gender, joinedDate, workingHours);
    }

    public abstract void treatPatient();

    /* GETTERS & SETTERS */

    public String getSpeciality() { return speciality; }

    public Doctor setSpeciality(String speciality)
    {
        this.speciality = speciality;
        return this;
    }
}
```

## Intern

```java
package Lab8.Hospital.Doctors;

import Lab8.Hospital.Gender;

import java.util.Date;

public class Intern extends Doctor
{
    private SeniorDoctor supervisor;

    public Intern(String name, Gender gender, Date joinedDate, int workingHours)
    {
        super(name, gender, joinedDate, workingHours);
    }

    @Override
    public void treatPatient()
    {
        System.out.println(this.getName() + " (Intern) is treating the patient under supervision of " + supervisor.getName());
    }

    /* GETTERS & SETTERS */

    public SeniorDoctor getSupervisor() { return supervisor; }

    public Intern setSupervisor(SeniorDoctor supervisor)
    {
        this.supervisor = supervisor;
        return this;
    }
}
```

## SeniorDoctor

```java
package Lab8.Hospital.Doctors;

import Lab8.Hospital.Gender;

import java.util.Date;

public class SeniorDoctor extends Doctor
{
    public SeniorDoctor(String name, Gender gender, Date joinedDate, int workingHours)
    {
        super(name, gender, joinedDate, workingHours);
    }

    @Override
    public void treatPatient()
    {
        System.out.println(this.getName() + " (Senior Doctor) is treating the patient");
    }
}
```

## Surgeon

```java
package Lab8.Hospital.Doctors;

import Lab8.Hospital.Gender;

import java.util.Date;

public class Surgeon extends Doctor
{
    public Surgeon(String name, Gender gender, Date joinedDate, int workingHours)
    {
        super(name, gender, joinedDate, workingHours);
    }

    @Override
    public void treatPatient()
    {
        System.out.println(this.getName() + " (Surgeon) is performing surgery on the patient");
    }
}
```

## Patient

```java
package Lab8.Hospital;

import Lab8.Hospital.Doctors.Doctor;

import java.time.Instant;
import java.util.Date;

public class Patient
{
    // start from -1 since the first staff member will have ID 0s
    private static long lastID = -1;

    private final long id;
    private final String name;
    private final Date birthDate;
    private final Gender gender;
    private Date dateAccepted;
    private String diagnosisReport;
    private Doctor treatingDoctor;
    private int daysStayed;

    public Patient(String name, Date birthDate, Gender gender)
    {
        this.id = ++lastID;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.dateAccepted = Date.from(Instant.now());
    }

    public void printInfo()
    {
        System.out.println("Patient Name: " + this.getName());
        System.out.println("Diagnosis Report: " + this.getDiagnosisReport());
        System.out.println("Doctor: " + this.getTreatingDoctor().getName());
        System.out.println("Days in Hospital: " + this.getDaysStayed());
    }

    /* GETTERS */

    public long getId() { return id; }

    public String getName() { return name; }

    public Date getBirthDate() { return birthDate; }

    public Gender getGender() { return gender; }

    public Date getDateAccepted() { return dateAccepted; }

    public String getDiagnosisReport() { return diagnosisReport; }

    public Doctor getTreatingDoctor() { return treatingDoctor; }

    public int getDaysStayed() { return daysStayed; }

    /* SETTERS */

    public Patient setDateAccepted(Date dateAccepted)
    {
        this.dateAccepted = dateAccepted;
        return this;
    }

    public Patient setDiagnosisReport(String diagnosisReport)
    {
        this.diagnosisReport = diagnosisReport;
        return this;
    }

    public Patient setTreatingDoctor(Doctor treatingDoctor)
    {
        this.treatingDoctor = treatingDoctor;
        return this;
    }

    public Patient setDaysStayed(int daysStayed)
    {
        this.daysStayed = daysStayed;
        return this;
    }
}
```

## Gender

```java
package Lab8.Hospital;

public enum Gender
{
    MALE,
    FEMALE
}
```