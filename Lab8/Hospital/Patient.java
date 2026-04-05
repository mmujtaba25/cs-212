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
        Date.from(Instant.now());
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
