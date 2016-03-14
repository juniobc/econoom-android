package com.econoom.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.econoom.entidade.UnidadeMedida;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Guarda as informações dos materiais
 * sebastiao junio 13/03/2016.
 */
public class UnMedidaDB {

    private BancoHelper bh;
    private static final String TABLE_UN_MEDIDA = "t0001";

    private static final String CD_UN_MEDIDA = "cd_un_medida";
    private static final String QT_UN_MEDIDA = "qt_un_meida";
    private static final String TP_UN_MEDIDA = "tp_un_medida";
    private static final String PK_UN_MED = "pk_un_med";

    private static final String TAG = "UnMedidaDB";

    protected static final String CREATE_MATERIAL_TABLE = "CREATE TABLE " + TABLE_UN_MEDIDA + "("
            + CD_UN_MEDIDA + " INTEGER primary key," + QT_UN_MEDIDA + " REAL, "
            + TP_UN_MEDIDA + " TEXT)";;

    public UnMedidaDB(Context contexto){

        bh = new BancoHelper(contexto);

    }

    public void deletaRegistro(int id){

        SQLiteDatabase db = bh.getReadableDatabase();

        String where = CD_UN_MEDIDA + "=" + id;

        db.delete(TABLE_UN_MEDIDA,where,null);
        db.close();

    }

    public void addUnMedida(UnidadeMedida unMedida) {
        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CD_UN_MEDIDA, unMedida.getCdUnMedida());
        values.put(QT_UN_MEDIDA, unMedida.getQtUnMedida());
        values.put(TP_UN_MEDIDA, unMedida.getTpUnMedida());

        db.insert(TABLE_UN_MEDIDA, null, values);
        db.close();
    }

    public void alteraRegistro(UnidadeMedida unMedida){

        SQLiteDatabase db = bh.getWritableDatabase();

        ContentValues valores;
        String where;

        where = CD_UN_MEDIDA + "=" + unMedida.getCdUnMedida();

        valores = new ContentValues();
        valores.put(CD_UN_MEDIDA, unMedida.getCdUnMedida());
        valores.put(QT_UN_MEDIDA, unMedida.getQtUnMedida());
        valores.put(TP_UN_MEDIDA, unMedida.getTpUnMedida());

        db.update(TABLE_UN_MEDIDA,valores,where,null);
        db.close();

    }

    public UnidadeMedida consultaUnMed(int id){

        SQLiteDatabase db = bh.getReadableDatabase();

        Cursor cursor;
        String[] campos = {CD_UN_MEDIDA,QT_UN_MEDIDA,TP_UN_MEDIDA};
        String where = CD_UN_MEDIDA + "=" + id;

        UnidadeMedida unMedida;

        cursor = db.query(TABLE_UN_MEDIDA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();

            unMedida = new UnidadeMedida(cursor.getInt(cursor.getColumnIndex(CD_UN_MEDIDA)),
                    cursor.getFloat(cursor.getColumnIndex(QT_UN_MEDIDA)),
                    cursor.getString(cursor.getColumnIndex(TP_UN_MEDIDA)));

        }else{unMedida = null;}


        db.close();

        return unMedida;

    }

    public List<UnidadeMedida> getTodasUnMedida() {

        SQLiteDatabase db = bh.getReadableDatabase();

        List<UnidadeMedida> unMedida = new ArrayList<UnidadeMedida>();
        UnidadeMedida unMed;

        Cursor cursor;
        String[] campos = {CD_UN_MEDIDA, QT_UN_MEDIDA, TP_UN_MEDIDA};
        cursor = db.query(TABLE_UN_MEDIDA, campos, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {

            do{
                unMed = new UnidadeMedida(cursor.getInt(cursor.getColumnIndex(CD_UN_MEDIDA)),
                cursor.getFloat(cursor.getColumnIndex(QT_UN_MEDIDA)),
                cursor.getString(cursor.getColumnIndex(TP_UN_MEDIDA)));

                unMedida.add(unMed);

                cursor.moveToNext();
            }while (cursor.moveToNext());

        }else{unMedida = null;}

        db.close();

        return unMedida;
    }

}
