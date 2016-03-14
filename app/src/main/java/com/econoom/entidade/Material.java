package com.econoom.entidade;

import android.content.Context;

import com.econoom.Banco.MaterialDB;

/**
 * Guarda as informações dos materiais
 * sebastiao junio 13/03/2016.
 */

public class Material {

    private int cdMat;
    private int cdCatl;
    private int cdUnMedida;
    private String dsMat;
    private String dtCad;

    public Material(int cdMat, int cdCatl, int cdUnMedida, String dsMat, String dtCad){
        this.cdMat = cdMat;
        this.cdUnMedida = cdUnMedida;
        this.dsMat = dsMat;
        this.cdCatl = cdCatl;
        this.cdCatl = cdCatl;
    }

    public void cadastro(Context contexto, int cdMat, int cdCatl, int cdUnMedida, String dsMat, String dtCad){

        MaterialDB db = new MaterialDB(contexto);

        Material m = new Material(cdMat, cdCatl, cdUnMedida, dsMat, dtCad);

        db.addMaterial(m);

    }


    public int getCdMat() {
        return cdMat;
    }

    public void setCdMat(int cdMat) {
        this.cdMat = cdMat;
    }

    public int getCdCatl() {
        return cdCatl;
    }

    public void setCdCatl(int cdCatl) {
        this.cdCatl = cdCatl;
    }

    public int getCdUnMedida() {
        return cdUnMedida;
    }

    public void setCdUnMedida(int cdUnMedida) {
        this.cdUnMedida = cdUnMedida;
    }

    public String getDsMat() {
        return dsMat;
    }

    public void setDsMat(String dsMat) {
        this.dsMat = dsMat.toUpperCase();
    }


    public String getDtCad() {
        return dtCad;
    }

    public void setDtCad(String dtCad) {
        this.dtCad = dtCad.toUpperCase();
    }
}
