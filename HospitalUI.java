import javax.swing.*;
import java.util.ArrayList;
import models.Patient;
import models.Doctor;
import models.Appointment;

public class HospitalUI {

    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {

        while(true){

            String choice = JOptionPane.showInputDialog(
                "Hospital Management System\n" +
                "1. Add Patient\n" +
                "2. Add Doctor\n" +
                "3. Schedule Appointment\n" +
                "4. View Patients\n" +
                "5. View Appointments\n" +
                "0. Exit"
            );

            if(choice == null || choice.equals("0")){
                break;
            }

            switch(choice){

                case "1":
                    addPatient();
                    break;

                case "2":
                    addDoctor();
                    break;

                case "3":
                    scheduleAppointment();
                    break;

                case "4":
                    viewPatients();
                    break;

                case "5":
                    viewAppointments();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice");
            }
        }
    }

    // ✅ Add Patient
    private static void addPatient(){
        String name = JOptionPane.showInputDialog("Enter Patient Name:");
        int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Age:"));
        String gender = JOptionPane.showInputDialog("Enter Gender:");

        Patient p = new Patient(name, gender, age);
        patients.add(p);

        JOptionPane.showMessageDialog(null, "Patient Added!");
    }

    // ✅ Add Doctor
    private static void addDoctor(){
        String name = JOptionPane.showInputDialog("Enter Doctor Name:");
        String spec = JOptionPane.showInputDialog("Enter Speciality:");

        Doctor d = new Doctor(name, spec);
        doctors.add(d);

        JOptionPane.showMessageDialog(null, "Doctor Added!");
    }

    // ✅ View Patients
    private static void viewPatients(){
        if(patients.isEmpty()){
            JOptionPane.showMessageDialog(null, "No patients found");
            return;
        }

        StringBuilder data = new StringBuilder();
        for(Patient p : patients){
            data.append(p).append("\n");
        }

        JOptionPane.showMessageDialog(null, data.toString());
    }

    // ✅ View Appointments
    private static void viewAppointments(){
        if(appointments.isEmpty()){
            JOptionPane.showMessageDialog(null, "No appointments found");
            return;
        }

        StringBuilder data = new StringBuilder();
        for(Appointment a : appointments){
            data.append(a).append("\n");
        }

        JOptionPane.showMessageDialog(null, data.toString());
    }

    // ✅ Schedule Appointment
    private static void scheduleAppointment(){

        if(patients.isEmpty() || doctors.isEmpty()){
            JOptionPane.showMessageDialog(null, "Add patients and doctors first!");
            return;
        }

        String pidStr = JOptionPane.showInputDialog("Enter Patient ID:");
        String didStr = JOptionPane.showInputDialog("Enter Doctor ID:");
        String date = JOptionPane.showInputDialog("Enter Date:");
        String time = JOptionPane.showInputDialog("Enter Time:");

        int pid = Integer.parseInt(pidStr);
        int did = Integer.parseInt(didStr);

        Patient selectedPatient = findPatientById(pid);
        Doctor selectedDoctor = findDoctorById(did);

        if(selectedPatient == null){
            JOptionPane.showMessageDialog(null, "Invalid Patient ID");
            return;
        }

        if(selectedDoctor == null){
            JOptionPane.showMessageDialog(null, "Invalid Doctor ID");
            return;
        }

        if(!isDoctorAvailable(selectedDoctor, date, time)){
            JOptionPane.showMessageDialog(null, "Doctor already booked!");
            return;
        }

        Appointment appt = new Appointment(selectedPatient, selectedDoctor, date, time);
        appointments.add(appt);

        JOptionPane.showMessageDialog(null, "Appointment Scheduled!");
    }

    // 🔍 Search
    private static Patient findPatientById(int id){
        for(Patient p : patients){
            if(p.getId() == id) return p;
        }
        return null;
    }

    private static Doctor findDoctorById(int id){
        for(Doctor d : doctors){
            if(d.getId() == id) return d;
        }
        return null;
    }

    // 🚫 Validation
    private static boolean isDoctorAvailable(Doctor doctor, String date, String time){
        for(Appointment a : appointments){
            if(a.getDoctor().getId() == doctor.getId() &&
               a.getDate().equals(date) &&
               a.getTime().equals(time)){
                return false;
            }
        }
        return true;
    }
}
