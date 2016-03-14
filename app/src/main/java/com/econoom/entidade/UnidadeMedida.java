package com.econoom.entidade;

/**
 * sebastiao junio 13/03/2016.
 */
public class UnidadeMedida {

    private int cdUnMedida;
    private float qtUnMedida;
    private String tpUnMedida;

    public UnidadeMedida(int cdUnMedida, float qtUnMedida, String tpUnMedida){

        this.cdUnMedida = cdUnMedida;
        this.qtUnMedida = qtUnMedida;
        this.tpUnMedida = tpUnMedida;

    }


    public int getCdUnMedida() {
        return cdUnMedida;
    }

    public void setCdUnMedida(int cdUnMedida) {
        this.cdUnMedida = cdUnMedida;
    }

    public float getQtUnMedida() {
        return qtUnMedida;
    }

    public void setQtUnMedida(float qtUnMedida) {
        this.qtUnMedida = qtUnMedida;
    }

    public String getTpUnMedida() {
        return tpUnMedida;
    }

    public void setTpUnMedida(String tpUnMedida) {
        this.tpUnMedida = tpUnMedida;
    }
}
