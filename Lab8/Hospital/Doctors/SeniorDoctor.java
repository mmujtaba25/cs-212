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
