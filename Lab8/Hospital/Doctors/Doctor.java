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
