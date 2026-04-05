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
