package com.econoom.entidade;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.econoom.Banco.GrupoMatDB;

import java.util.ArrayList;
import java.util.List;


public class ClasseMat implements Parcelable {

    private static final String TAG = "ClasseMat";

    private int cdClasseMat;
    private int cdGprMat;
    private String dsClasseMat;
    private byte[] imgClasseMat;

    public ClasseMat(){}

    public ClasseMat(int cdClasseMat, int cdGprMat, String dsClasseMat, byte[] imgClasseMat){

        this.cdClasseMat = cdClasseMat;
        this.cdGprMat = cdGprMat;
        this.dsClasseMat = dsClasseMat.toUpperCase();
        this.imgClasseMat = imgClasseMat;
    }

    public ClasseMat(Parcel read) {
        cdClasseMat = read.readInt();
        cdGprMat = read.readInt();
        dsClasseMat = read.readString();
        read.readByteArray(imgClasseMat);
    }

    public void cadastro(Context contexto){

        try{

            GrupoMatDB db = new GrupoMatDB(contexto);

            GrupoMat gpr = new GrupoMat(this.dsGrupo, this.imgGprMat);

            db.addGprMat(gpr);

            Toast.makeText(contexto, "Grupo cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

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

    public boolean alteraGprMat(Context contexto){

        try{

            GrupoMatDB db = new GrupoMatDB(contexto);

            GrupoMat gpr = new GrupoMat(this.cdGprMat, this.dsGrupo, this.imgGprMat);

            db.alteraRegistro(gpr);

            Toast.makeText(contexto, "Grupo alterado com sucesso!", Toast.LENGTH_SHORT).show();

            return true;

        }catch (SQLiteConstraintException e){
            Log.e(TAG, e.getMessage());
            Toast.makeText(contexto, "Grupo de Material já cadastrado!", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public int getCdClasseMat() {
        return cdClasseMat;
    }

    public int getCdGprMat() {
        return cdGprMat;
    }

    public void setCdGprMat(int cdGprMat){
        this.cdGprMat = cdGprMat;
    }

    public String getDsClasseMat() {
        return dsClasseMat;
    }

    public void setDsClasseMat(String dsClasseMat) {
        this.dsClasseMat = dsClasseMat.toUpperCase();
    }

    public byte[] getImgClasseMat() {
        return imgClasseMat;
    }

    public void setImgClasseMat(byte[] imgClasseMat) {
        this.imgClasseMat = imgClasseMat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Parcelable.Creator<ClasseMat> CREATOR =
            new Parcelable.Creator<ClasseMat>() {
                @Override
                public ClasseMat createFromParcel(Parcel source) {
                    return new ClasseMat(source);
                }
                @Override
                public ClasseMat[] newArray(int size) {
                    return new ClasseMat[size];
                }
            };
}
