package org.example;

import java.util.Objects;

public class Student {
    private int idStudent;
    private String numeStudent;
    private double bugetStudent;

    public Student(int idStudent, String numeStudent, double bugetStudent) {
        this.idStudent = idStudent;
        this.numeStudent = numeStudent;
        this.bugetStudent = bugetStudent;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public String getNumeStudent() {
        return numeStudent;
    }

    public void setNumeStudent(String numeStudent) {
        this.numeStudent = numeStudent;
    }

    public double getBugetStudent() {
        return bugetStudent;
    }

    public void setBugetStudent(double bugetStudent) throws BugetInvalidException {
        if (bugetStudent < 0) {
            throw new BugetInvalidException("Studentul nu are suficienti bani");
        }
        this.bugetStudent = bugetStudent;
    }

    @Override
    public String toString() {
        return idStudent+","+numeStudent+","+bugetStudent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getIdStudent() == student.getIdStudent() && Double.compare(student.getBugetStudent(), getBugetStudent()) == 0 && Objects.equals(getNumeStudent(), student.getNumeStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdStudent(), getNumeStudent(), getBugetStudent());
    }
}
