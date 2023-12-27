package medchek.models;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private Long id;
    private String address;
    private List<Department> departments =new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();

    private static long counter = 0;
    public Long hospIdMaker(){
        ++ counter;
        return this.id = counter;
    }

    public Hospital() {
    }

    public Hospital(String address) {
        this.address = address;
    }

    public Hospital(String address, List<Department> departments, List<Doctor> doctors, List<Patient> patients) {
        this.address = address;
        this.departments = departments;
        this.doctors = doctors;
        this.patients = patients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", departments=" + departments +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
