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
