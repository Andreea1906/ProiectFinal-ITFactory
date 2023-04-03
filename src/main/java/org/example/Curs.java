package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Curs {
    private int idCurs;
    private String numeCurs;
    private double pretCurs;

    private LocalDate dataInceput;

    public Curs(int idCurs, String numeCurs, double pretCurs, LocalDate dataInceput) {
        this.idCurs = idCurs;
        this.numeCurs = numeCurs;
        this.pretCurs = pretCurs;
        this.dataInceput = dataInceput;
    }

    @Override
    public String toString() {
        return idCurs+","+numeCurs+","+pretCurs+","+dataInceput;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curs curs)) return false;
        return getIdCurs() == curs.getIdCurs() && Double.compare(curs.getPretCurs(), getPretCurs()) == 0 && Objects.equals(getNumeCurs(), curs.getNumeCurs()) && Objects.equals(getDataInceput(), curs.getDataInceput());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCurs(), getNumeCurs(), getPretCurs(), getDataInceput());
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(LocalDate dataInceput) {
        this.dataInceput = dataInceput;
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public String getNumeCurs() {
        return numeCurs;
    }

    public void setNumeCurs(String numeCurs) {
        this.numeCurs = numeCurs;
    }

    public double getPretCurs() {
        return pretCurs;
    }

    public void setPretCurs(double pretCurs) {
        this.pretCurs = pretCurs;
    }

}
