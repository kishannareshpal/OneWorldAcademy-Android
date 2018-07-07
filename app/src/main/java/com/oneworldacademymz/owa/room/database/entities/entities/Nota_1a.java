package com.oneworldacademymz.owa.room.database.entities.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "notas_1a")
public class Nota_1a { // 1a Classe

    @PrimaryKey
    private int id;

    // Português
    private int p_1;
    private int p_2;
    private int p_3;
    private int p_nf;

    // Matemática
    private int ma_1;
    private int ma_2;
    private int ma_3;
    private int ma_nf;

    // Inglês
    private int i_1;
    private int i_2;
    private int i_3;
    private int i_nf;

    // Artes Visuais
    private int av_1;
    private int av_2;
    private int av_3;
    private int av_nf;

    // Música
    private int mu_1;
    private int mu_2;
    private int mu_3;
    private int mu_nf;

    // Educação Física
    private int ef_1;
    private int ef_2;
    private int ef_3;
    private int ef_nf;

    // Dança
    private int d_1;
    private int d_2;
    private int d_3;
    private int d_nf;








    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP_1() {
        return p_1;
    }

    public void setP_1(int p_1) {
        this.p_1 = p_1;
    }

    public int getP_2() {
        return p_2;
    }

    public void setP_2(int p_2) {
        this.p_2 = p_2;
    }

    public int getP_3() {
        return p_3;
    }

    public void setP_3(int p_3) {
        this.p_3 = p_3;
    }

    public int getP_nf() {
        return p_nf;
    }

    public void setP_nf(int p_nf) {
        this.p_nf = p_nf;
    }

    public int getMa_1() {
        return ma_1;
    }

    public void setMa_1(int ma_1) {
        this.ma_1 = ma_1;
    }

    public int getMa_2() {
        return ma_2;
    }

    public void setMa_2(int ma_2) {
        this.ma_2 = ma_2;
    }

    public int getMa_3() {
        return ma_3;
    }

    public void setMa_3(int ma_3) {
        this.ma_3 = ma_3;
    }

    public int getMa_nf() {
        return ma_nf;
    }

    public void setMa_nf(int ma_nf) {
        this.ma_nf = ma_nf;
    }

    public int getI_1() {
        return i_1;
    }

    public void setI_1(int i_1) {
        this.i_1 = i_1;
    }

    public int getI_2() {
        return i_2;
    }

    public void setI_2(int i_2) {
        this.i_2 = i_2;
    }

    public int getI_3() {
        return i_3;
    }

    public void setI_3(int i_3) {
        this.i_3 = i_3;
    }

    public int getI_nf() {
        return i_nf;
    }

    public void setI_nf(int i_nf) {
        this.i_nf = i_nf;
    }

    public int getAv_1() {
        return av_1;
    }

    public void setAv_1(int av_1) {
        this.av_1 = av_1;
    }

    public int getAv_2() {
        return av_2;
    }

    public void setAv_2(int av_2) {
        this.av_2 = av_2;
    }

    public int getAv_3() {
        return av_3;
    }

    public void setAv_3(int av_3) {
        this.av_3 = av_3;
    }

    public int getAv_nf() {
        return av_nf;
    }

    public void setAv_nf(int av_nf) {
        this.av_nf = av_nf;
    }

    public int getMu_1() {
        return mu_1;
    }

    public void setMu_1(int mu_1) {
        this.mu_1 = mu_1;
    }

    public int getMu_2() {
        return mu_2;
    }

    public void setMu_2(int mu_2) {
        this.mu_2 = mu_2;
    }

    public int getMu_3() {
        return mu_3;
    }

    public void setMu_3(int mu_3) {
        this.mu_3 = mu_3;
    }

    public int getMu_nf() {
        return mu_nf;
    }

    public void setMu_nf(int mu_nf) {
        this.mu_nf = mu_nf;
    }

    public int getEf_1() {
        return ef_1;
    }

    public void setEf_1(int ef_1) {
        this.ef_1 = ef_1;
    }

    public int getEf_2() {
        return ef_2;
    }

    public void setEf_2(int ef_2) {
        this.ef_2 = ef_2;
    }

    public int getEf_3() {
        return ef_3;
    }

    public void setEf_3(int ef_3) {
        this.ef_3 = ef_3;
    }

    public int getEf_nf() {
        return ef_nf;
    }

    public void setEf_nf(int ef_nf) {
        this.ef_nf = ef_nf;
    }

    public int getD_1() {
        return d_1;
    }

    public void setD_1(int d_1) {
        this.d_1 = d_1;
    }

    public int getD_2() {
        return d_2;
    }

    public void setD_2(int d_2) {
        this.d_2 = d_2;
    }

    public int getD_3() {
        return d_3;
    }

    public void setD_3(int d_3) {
        this.d_3 = d_3;
    }

    public int getD_nf() {
        return d_nf;
    }

    public void setD_nf(int d_nf) {
        this.d_nf = d_nf;
    }
}
