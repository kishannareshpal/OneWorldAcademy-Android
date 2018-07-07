package com.oneworldacademymz.owa.room.database.entities.entities;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "mensalidades")
public class Mensalidade {


    @PrimaryKey
    private int id;
    private int payment_year;

    private boolean fev;
    private boolean mar;
    private boolean abr;
    private boolean mai;
    private boolean jun;
    private boolean jul;
    private boolean ago;
    private boolean set;
    private boolean out;
    private boolean nov;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment_year() {
        return payment_year;
    }

    public void setPayment_year(int payment_year) {
        this.payment_year = payment_year;
    }

    public boolean isFev() {
        return fev;
    }

    public void setFev(boolean fev) {
        this.fev = fev;
    }

    public boolean isMar() {
        return mar;
    }

    public void setMar(boolean mar) {
        this.mar = mar;
    }

    public boolean isAbr() {
        return abr;
    }

    public void setAbr(boolean abr) {
        this.abr = abr;
    }

    public boolean isMai() {
        return mai;
    }

    public void setMai(boolean mai) {
        this.mai = mai;
    }

    public boolean isJun() {
        return jun;
    }

    public void setJun(boolean jun) {
        this.jun = jun;
    }

    public boolean isJul() {
        return jul;
    }

    public void setJul(boolean jul) {
        this.jul = jul;
    }

    public boolean isAgo() {
        return ago;
    }

    public void setAgo(boolean ago) {
        this.ago = ago;
    }

    public boolean isSet() {
        return set;
    }

    public void setSet(boolean set) {
        this.set = set;
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public boolean isNov() {
        return nov;
    }

    public void setNov(boolean nov) {
        this.nov = nov;
    }
}
