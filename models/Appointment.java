package models;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    public Appointment(Patient patient,Doctor doctor,String date,String time){
        this.patient=patient;
        this.doctor=doctor;
        this.date=date;
        this.time = time;
    }
    public String toString(){
    return "\nAppointment Details:\n" +
           "----------------------\n" +
           "Patient: " + patient + "\n" +
           "Doctor: " + doctor + "\n" +
           "Time : " + time + "\n" +
           "Date: " + date + "\n";
    }
    public Doctor getDoctor(){
    return doctor;
}

public Patient getPatient(){
    return patient;
}

public String getDate(){
    return date;
}

public String getTime(){
    return time;
}

}
