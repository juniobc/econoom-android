package com.econoom.entidade;

/**
 * sebastiao junio 13/03/2016.
 */
public class GrupoMat {

    private int cdGprMat;
    private String dsGrupo;
    private byte[] imgGprMat;

    public GrupoMat(int cdGprMat, String dsGrupo, byte[] imgGprMat){

        this.cdGprMat = cdGprMat;
        this.dsGrupo = dsGrupo;
        this.imgGprMat = imgGprMat;

    }


    public int getCdGprMat() {
        return cdGprMat;
    }

    public void setCdGprMat(int cdGprMat) {
        this.cdGprMat = cdGprMat;
    }

    public String getDsGrupo() {
        return dsGrupo;
    }

    public void setDsGrupo(String dsGrupo) {
        this.dsGrupo = dsGrupo;
    }


    public byte[] getImgGprMat() {
        return imgGprMat;
    }

    public void setImgGprMat(byte[] imgGprMat) {
        this.imgGprMat = imgGprMat;
    }
}
