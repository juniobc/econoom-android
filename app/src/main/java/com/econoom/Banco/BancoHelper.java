package com.econoom.Banco;

/**
 * Guarda as informações dos materiais
 * sebastiao junio 13/03/2016.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class BancoHelper extends SQLiteOpenHelper {

    private static final String TAG = "BancoHelper";

    private static final int DATABASE_VERSION = 7;

    private static final String DATABASE_NAME = "econoom";

    public BancoHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(GrupoMatDB.CREATE_GRP_MAT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.d(TAG,"onUpgrade - oldVersion: "+oldVersion);

        db.execSQL(GrupoMatDB.DROP_GRP_MAT_TABLE);
        db.execSQL(GrupoMatDB.CREATE_GRP_MAT_TABLE);

    }
}
