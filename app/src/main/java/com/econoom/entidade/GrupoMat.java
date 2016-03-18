package com.econoom.entidade;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.econoom.Banco.GrupoMatDB;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * sebastiao junio 13/03/2016.
 */
public class GrupoMat implements Parcelable {

    private static final String TAG = "GrupoMat";

    private int cdGprMat;
    private String dsGrupo;
    private byte[] imgGprMat;

    public GrupoMat(){}

    public GrupoMat(int cdGprMat, String dsGrupo, byte[] imgGprMat){

        this.dsGrupo = dsGrupo.toUpperCase();
        this.imgGprMat = imgGprMat;
        this.cdGprMat = cdGprMat;

    }

    public GrupoMat(String dsGrupo, byte[] imgGprMat){

        this.dsGrupo = dsGrupo.toUpperCase();
        this.imgGprMat = imgGprMat;

    }

    public GrupoMat(Parcel read) {
        cdGprMat = read.readInt();
        dsGrupo = read.readString();
        read.readByteArray(imgGprMat);
    }

    public void cadastro(Context contexto){

        try{

            GrupoMatDB db = new GrupoMatDB(contexto);

            GrupoMat gpr = new GrupoMat(this.dsGrupo, this.imgGprMat);

            db.addGprMat(gpr);

        }catch (SQLiteConstraintException e){
            Log.e(TAG, e.getMessage());
            Toast.makeText(contexto, "Grupo de Material já cadastrado!", Toast.LENGTH_LONG).show();
        }

    }

    public List<GrupoMat> listaTodosGprMat(Context contexto){

        List<GrupoMat> grupoMatList = new ArrayList<GrupoMat>();

        GrupoMatDB db = new GrupoMatDB(contexto);

        grupoMatList = db.getTodosGprMat();

        return grupoMatList;


    }

    public void deletaGrpMat(Context contexto){

        GrupoMatDB db = new GrupoMatDB(contexto);

        db.deletaRegistro(this.cdGprMat);

    }

    public void alteraGprMat(Context contexto){

        GrupoMatDB db = new GrupoMatDB(contexto);

        GrupoMat gpr = new GrupoMat(this.cdGprMat, this.dsGrupo, this.imgGprMat);

        db.alteraRegistro(gpr);

    }


    public int getCdGprMat() {
        return cdGprMat;
    }

    public String getDsGrupo() {
        return dsGrupo;
    }

    public void setDsGrupo(String dsGrupo) {
        this.dsGrupo = dsGrupo.toUpperCase();
    }

    public byte[] getImgGprMat() {
        return imgGprMat;
    }

    public void setImgGprMat(byte[] imgGprMat) {
        this.imgGprMat = imgGprMat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<GrupoMat> CREATOR =
    new Parcelable.Creator<GrupoMat>() {
        @Override
        public GrupoMat createFromParcel(Parcel source) {
            return new GrupoMat(source);
        }
        @Override
        public GrupoMat[] newArray(int size) {
            return new GrupoMat[size];
        }
    };
}
