package com.econoom.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.econoom.entidade.Material;

/**
 * Guarda as informações dos materiais
 * sebastiao junio 13/03/2016.
 */
public class MaterialDB {

    private BancoHelper bh;
    private static final String TABLE_MATERIAL = "t0001";

    private static final String CD_MAT = "cd_mat";
    private static final String CD_CATL = "cd_catl";
    private static final String CD_UN_MEDIDA = "cd_un_medida";
    private static final String DS_MAT = "ds_mat";
    private static final String DT_CAD = "dt_cad";
    private static final String PK_MAT = "pk_mat";

    private static final String TAG = "UsuarioDB";

    protected static final String CREATE_MATERIAL_TABLE = "CREATE TABLE " + TABLE_MATERIAL + "("
            + CD_MAT + " INTEGER," + CD_CATL + " INTEGER," + CD_UN_MEDIDA + " INTEGER,"
            + DS_MAT + " TEXT," + DT_CAD + ", PRIMARY KEY ("+CD_MAT+","+CD_CATL+", "+CD_UN_MEDIDA+") )";

    public MaterialDB(Context contexto){

        bh = new BancoHelper(contexto);

    }

    public void deletaRegistro(int id){

        SQLiteDatabase db = bh.getReadableDatabase();

        String where = CD_MAT + "=" + id;

        db.delete(TABLE_MATERIAL,where,null);
        db.close();

    }

    public void addMaterial(Material mat) {
        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CD_MAT, mat.getCdMat());
        values.put(CD_CATL, mat.getCdCatl());
        values.put(CD_UN_MEDIDA, mat.getCdUnMedida());
        values.put(DS_MAT, mat.getDsMat());
        values.put(DT_CAD, mat.getDtCad());

        db.insert(TABLE_MATERIAL, null, values);
        db.close();
    }

    public void alteraRegistro(Material mat){

        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues valores;
        String where;

        bh.getWritableDatabase();
        where = CD_MAT + "=" + mat.getCdMat();

        valores = new ContentValues();
        valores.put(CD_MAT, mat.getCdMat());
        valores.put(CD_CATL, mat.getCdCatl());
        valores.put(CD_UN_MEDIDA, mat.getCdUnMedida());
        valores.put(DS_MAT, mat.getDsMat());
        valores.put(DT_CAD, mat.getDtCad());

        db.update(TABLE_MATERIAL,valores,where,null);
        db.close();

    }


    public Material getTodosMateriais() {

        Material material;

        String selectQuery = "SELECT "+CD_MAT+", "+CD_CATL+", "+CD_UN_MEDIDA+", "+DS_MAT+", "+DT_CAD
                +" FROM " + TABLE_MATERIAL;

        SQLiteDatabase db = bh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                material = new Material(cursor.getInt(cursor.getColumnIndex(CD_MAT)),
                        cursor.getInt(cursor.getColumnIndex(CD_CATL)),
                        cursor.getInt(cursor.getColumnIndex(CD_UN_MEDIDA)),
                        cursor.getString(cursor.getColumnIndex(DS_MAT)),
                        cursor.getString(cursor.getColumnIndex(DT_CAD))
                );
            } while (cursor.moveToNext());
        }else{
            material = null;
        }

        return material;
    }
}
