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
