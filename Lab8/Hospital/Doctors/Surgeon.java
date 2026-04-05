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
