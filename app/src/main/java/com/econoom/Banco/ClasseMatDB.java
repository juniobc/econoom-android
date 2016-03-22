package com.econoom.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ClasseMatDB {

    private BancoHelper bh;
    private static final String TABLE_CLASSE_MAT = "t0003";

    protected static final String CD_CLASSE_MAT = "cd_classe_mat";
    private static final String CD_GPR_MAT = "cd_gpr_mat";
    private static final String DS_CLASSE_MAT = "ds_classe_mat";
    private static final String IMG_CLASSE_MAT = "img_classe_mat";

    private static final String TAG = "ClasseMatDB";

    protected static final String CREATE_CLASSE_MAT_TABLE = "CREATE TABLE " + TABLE_CLASSE_MAT + "("
            +CD_CLASSE_MAT + " INTEGER PRIMARY KEY, "+CD_GPR_MAT+" INTEGER,"
            + DS_CLASSE_MAT + " TEXT NOT NULL UNIQUE, "+IMG_CLASSE_MAT+" BLOB, "
            +"FOREIGN KEY(" + CD_GPR_MAT + ") REFERENCES t0002("+GrupoMatDB.CD_GPR_MAT+"))";

    protected static final String DROP_CLASSE_MAT_TABLE = "DROP TABLE "+TABLE_CLASSE_MAT;

    public ClasseMatDB(Context contexto){

        bh = new BancoHelper(contexto);

    }

    public void deletaRegistro(int id){

        SQLiteDatabase db = bh.getReadableDatabase();

        String where = CD_GPR_MAT + "=" + id;

        db.delete(TABLE_GPR_MAT,where,null);
        db.close();

    }

    public void addGprMat(GrupoMat gprMat) {
        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(CD_GPR_MAT, gprMat.getCdGprMat());
        values.put(DS_GPR_MAT, gprMat.getDsGrupo());
        values.put(IMG_GPR_MAT, gprMat.getImgGprMat());

        db.insertOrThrow(TABLE_GPR_MAT, null, values);
        db.close();
    }

    public void alteraRegistro(GrupoMat gprMat){
        Log.d(TAG, "alteraRegistro - passou descricao: " + gprMat.getDsGrupo());
        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues valores;
        String where;

        where = CD_GPR_MAT + "=" + gprMat.getCdGprMat();

        valores = new ContentValues();
        valores.put(CD_GPR_MAT, gprMat.getCdGprMat());
        valores.put(DS_GPR_MAT, gprMat.getDsGrupo());
        valores.put(IMG_GPR_MAT, gprMat.getImgGprMat());

        db.update(TABLE_GPR_MAT,valores,where,null);
        db.close();

    }

    public GrupoMat consultaGprMat(int id){

        SQLiteDatabase db = bh.getReadableDatabase();

        Cursor cursor;
        String[] campos = {CD_GPR_MAT,DS_GPR_MAT,IMG_GPR_MAT};
        String where = CD_GPR_MAT + "=" + id;

        GrupoMat gprMat;

        cursor = db.query(TABLE_GPR_MAT,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            gprMat = new GrupoMat(cursor.getInt(cursor.getColumnIndex(CD_GPR_MAT)),
                    cursor.getString(cursor.getColumnIndex(DS_GPR_MAT)),
                    cursor.getBlob(cursor.getColumnIndex(IMG_GPR_MAT))
            );

        }else{gprMat = null;}


        db.close();

        return gprMat;

    }

    public List<GrupoMat> getTodosGprMat() {

        SQLiteDatabase db = bh.getReadableDatabase();

        List<GrupoMat> listGpr = new ArrayList<GrupoMat>();
        GrupoMat gprMat;

        Cursor cursor;
        String[] campos = {CD_GPR_MAT, DS_GPR_MAT, IMG_GPR_MAT};
        cursor = db.query(TABLE_GPR_MAT, campos, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do{
                gprMat = new GrupoMat(cursor.getInt(cursor.getColumnIndex(CD_GPR_MAT)),
                        cursor.getString(cursor.getColumnIndex(DS_GPR_MAT)),
                        cursor.getBlob(cursor.getColumnIndex(IMG_GPR_MAT)));

                listGpr.add(gprMat);

            }while (cursor.moveToNext());

        }else{listGpr = null;}


        return listGpr;
    }

}
