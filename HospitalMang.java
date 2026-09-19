import java.util.Scanner;
import java.util.ArrayList;
import models.Patient;
import models.Appointment;
import models.Doctor;

public class HospitalMang {

    private static ArrayList<Patient> patients=new ArrayList<>();
    private static ArrayList<Doctor> doctors=new ArrayList<>();
    private static ArrayList<Appointment> Appointments = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do{

        System.out.println("Hospital Management System");
        System.out.println("1. Add Patient : ");
        System.out.println("2. Add Doctor : ");
        System.out.println("3. Schedule Appointment :");
        System.out.println("4. View Patient :");
        System.out.println("5. View Appointment :");
        System.out.println("0. Exit :");

        System.out.println("Enter Your choice ");
        choice =sc.nextInt();

        switch(choice){
            case 1:
                addPatient(sc);
                break;
            case 2:
                addDoctor(sc);
                break;
            case 3:
                scheduleAppointment(sc);
                break;
            case 4:
                viewPatients();
                break;
            case 5:
                viewAppointments();
                break;
            case 0:
                System.out.println("Exiting....");
                break;
            default:
                System.out.println("Invalid command");
        }
    }
    while(choice!=0);
    }

    private static void addPatient(Scanner sc){

        sc.nextLine();
        System.out.println("Enter Patient's Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Patient's Age : ");
        int  age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Patient's Gender : ");
        String gender = sc.nextLine();
        Patient patient = new Patient(name, gender, age);
        patients.add(patient);

        System.out.println("Patient added successfully");

    }

    private static void addDoctor(Scanner sc){

        sc.nextLine();
        System.out.println("Enter Doctor's Name : ");
        String name = sc.nextLine();
        System.out.println("Enter Doctor's Speciality : ");
        String speciality = sc.nextLine();

        Doctor doctor = new Doctor(name,speciality);
        doctors.add(doctor);

        System.out.println("Doctor added successfully");

    }

    private static void viewPatients(){
        if(patients.isEmpty()){
            System.out.println("No patient found ");
            return;
        }
        System.out.println(".....The list of patients is as following .....");
        for(Patient x: patients){
            System.out.println(x);
        }
       
    }

    private static void scheduleAppointment(Scanner sc){
        if(patients.isEmpty() || doctors.isEmpty()){
            System.out.println("Please add patients and doctors first");
            return;
        }
        System.out.println("\n ...Patients List ...");
        for(Patient p:patients)System.out.println(p);

        System.out.println("Enter Patient ID :");
        int pid=sc.nextInt();

        sc.nextLine();

        System.out.println("\n ...Doctors List ...");
        for(Doctor p:doctors)System.out.println(p);

        System.out.println("Enter Doctor ID :");
        int did=sc.nextInt();

        sc.nextLine();

        System.out.println("Enter date of Appointment(XX/XX/XXXX) : ");
        String date=sc.nextLine();

        System.out.print("Enter Time (HH:MM) : ");
        String time = sc.nextLine();

        Patient selected_patient=null;
        Doctor selected_doctor=null;

        Patient p = findPatientById(pid);
        selected_patient=p;

        Doctor d = findDoctorById(did);
        selected_doctor=d;

        if(selected_patient == null){
        System.out.println("Invalid Patient ID!");
        return;
        }

        if(selected_doctor == null){
        System.out.println("Invalid Doctor ID!");
        return;
        }

    if(!isDoctorAvailable(selected_doctor, date, time)){
        System.out.println("Doctor is already booked at this time!");
        return;
    }


        
        Appointment appt=new Appointment(selected_patient, selected_doctor, date,time);
        Appointments.add(appt);
        System.out.println("Appointment Scheduled Successfully");
    }

    private static void viewAppointments(){
        if(Appointments.isEmpty()){
            System.out.println("No Appointments Scheduled yet ");
            return;
        }
        System.out.println(".....The list of Appointments is as following .....");
        for(Appointment a : Appointments){
            System.out.println("----------------------");
            System.out.println(a);
        }
    }

    private static Patient findPatientById(int id){
    for(Patient p : patients){
        if(p.getId() == id){
            return p;
        }
    }
    return null;
   }
   private static Doctor findDoctorById(int id){
    for(Doctor d : doctors){
        if(d.getId() == id){
            return d;
        }
    }
    return null;
    }

    private static boolean isDoctorAvailable(Doctor doctor, String date, String time){

    for(Appointment a : Appointments){
        if(a.getDoctor().getId() == doctor.getId() &&
           a.getDate().equals(date) &&
           a.getTime().equals(time)){
            return false; // already booked
        }
    }
    return true;
}

}
