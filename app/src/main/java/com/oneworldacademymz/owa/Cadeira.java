package com.oneworldacademymz.owa;

public class Cadeira {


    private String cadeira_name, prim_trim, seg_trim, ter_trim, nota_final;
    private int id;


    public Cadeira(String cadeira_name, int id, String prim_trim, String seg_trim, String ter_trim, String nota_final) {
        this.cadeira_name = cadeira_name;
        this.id = id;
        this.prim_trim = prim_trim;
        this.seg_trim = seg_trim;
        this.ter_trim = ter_trim;
        this.nota_final = nota_final;
//        this.cardcolor = cardcolor;
    }

//    public String getCardColor() { return cardcolor; }

    public String getCadeira_name() {
        return cadeira_name;
    }

    public int getId() {
        return id;
    }

    public String getPrim_trim() {
        return prim_trim;
    }

    public String getSeg_trim() {
        return seg_trim;
    }

    public String getTer_trim() {
        return ter_trim;
    }

    public String getNota_final() {
        return nota_final;
    }
}
