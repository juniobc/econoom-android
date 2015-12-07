package com.econoom.Banco;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import java.util.*;
import com.econoom.entidade.*;

public class NotaServicoDB extends SQLiteOpenHelper
{
	
	private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "econoom";

    private static final String TABLE_PRODUTO = "produtos";

    private static final String KEY_ID = "cd_produto";
	private static final String KEY_TP_NT = "tp_nt"
    private static final String KEY_NM_PROD = "nm_prod";
    private static final String KEY_TP_UN_PROD = "tp_un_prod";
    private static final String KEY_QT_TP_UN = "qt_tp_un";
    private static final String KEY_PRECO = "vl_prod";
    private static final String KEY_QUANT = "qt_prod";
	private static final String KEY_CD_BARRA= "cd_barra";
	private static final String KEY_LAT = "cd_Lat";
	private static final String KEY_LONG = "cd_long";
	private static final String KEY_TP_CAD = "tp_cad";
	private static final String KEY_DT_HR_CAD = "dt_hr_cad";

    public NotaServicoDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUTOS_TABLE = "CREATE TABLE " + TABLE_PRODUTO + "("
        	+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TP_NT + " INTEGER," + KEY_CD_BARRA + " TEXT,"
        	+ KEY_NM_PROD + " TEXT," + KEY_TP_UN_PROD + " TEXT," 
        	+ KEY_QT_TP_UN + "REAL,"
			+ KEY_PRECO + " REAL," + KEY_QUANT + " INTEGER,"
			+ KEY_LAT + " TEXT,"
			+ KEY_LONG + " TEXT," + KEY_TP_CAD + " INTEGER," + KEY_DT_HR_CAD + " INTEGER	" + ")";
        db.execSQL(CREATE_PRODUTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);

        onCreate(db);
    }

    public void addProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        int tpCad;

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, produto.getNome());
        values.put(KEY_TP_UN_PROD, produto.getTpUnidadeMedida());
        values.put(KEY_QT_TP_UN, produto.getQtUnMedida());
        values.put(KEY_PRECO, Float.toString((produto.valor));
        values.put(KEY_QUANT, produto.getQuantidade());_
		values.put(KEY_LAT, produto.getNrLat());
		values.put(KEY_LONG, produto.getNrLong());
		if(produto.getTpCad()){
			tpCad = 1;
		}else{

			tpCad = 0;

		}
		values.put(KEY_TP_CAD, tpCad);
		values.put(KEY_DT_HR_CAD, System.currentTimeMillis()); 

        db.insert(TABLE_PRODUTO, null, values);
        db.close();
    }


    NotaServicoDB getProduto(int nm_prod) {
        SQLiteDatabase db = this.getReadableDatabase();
        Boolean tpCad;

        Cursor cursor = db.query(TABLE_PRODUTO, new String[] { KEY_ID,
									 KEY_NM_PROD, KEY_TP_UN_PROD, KEY_QT_TP_UN, KEY_PRECO, KEY_CD_BARRA, KEY_QUANT, 
									 KEY_LAT, KEY_LONG, KEY_TP_CAD, KEY_DT_HR_CAD }, KEY_NM_PROD + "=?",
								 new String[] { String.valueOf(nm_prod) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        if(cursor.getInt(8) == 0){

        	tpCad = false;

        }else{

        	tpCad = true;

        }

		NotaServicoDB produto = new NotaServicoDB(cursor.getInt(0), cursor.getString(1), cursor.getString(2), 
									  cursor.getFloat(3), cursor.getFloat(4), cursor.getInt(5), cursor.getString(6), 
									  cursor.getString(7), tpCad, cursor.getInt(9));

        return produto;
    }

    // Getting All Contacts
    public List<NotaServicoDB> getAllProdutos() {
        List<NotaServicoDB> produtoList = new ArrayList<NotaServicoDB>();
        Boolean tpCad;
        // Select All Query
        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_TP_UN_PROD+", "+KEY_QT_TP_UN+", "+KEY_PRECO+", "+KEY_QUANT+", "
			+KEY_CD_BARRA+", "+KEY_LAT+", "+KEY_LONG+", "+KEY_TP_CAD+", "+KEY_DT_HR_CAD+" FROM " + TABLE_PRODUTO + " ORDER BY cd_produto DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                NotaServicoDB produto = new NotaServicoDB();
                produto.setCdProd(cursor.getInt(0));
                produto.setNome(cursor.getString(1));
                produto.setTpUnidade(cursor.getString(2));
                produto.setQtUnidade(cursor.getFloat(3));
				produto.setPreco(cursor.getFloat(4));
				produto.setQuantidade(cursor.getInt(5));
				produto.setCdBarra(cursor.getString(6));
				produto.setNrLat(cursor.getString(7));
				produto.setNrLong(cursor.getString(8));
				if(cursor.getInt(9) == 0){

		        	tpCad = false;

		        }else{

		        	tpCad = true;

		        }
				produto.setDt_hr(cursor.getInt(10));

				produto.setTpCad(tpCad);


                produtoList.add(produto);
            } while (cursor.moveToNext());
        }


        return produtoList;
    }


    public int updateProduto(NotaServicoDB produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        int tpCad;

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, produto.getNome());
        values.put(KEY_TP_UN_PROD, produto.getTpUnidade());
        values.put(KEY_QT_TP_UN, produto.getQtUnidade());
        values.put(KEY_PRECO, produto.getPreco());
        values.put(KEY_CD_BARRA, produto.getCdBarra());
        values.put(KEY_QUANT, produto.getQuantidade());
        values.put(KEY_LAT, produto.getNrLat());
        values.put(KEY_LONG, produto.getNrLong());

        if(!produto.getTpCad()){

        	tpCad = 0;

        }else{

        	tpCad = 1;

        }

        values.put(KEY_TP_CAD, tpCad);


        return db.update(TABLE_PRODUTO, values, KEY_ID + " = ?",
						 new String[] { String.valueOf(produto.getNome()) });
    }


    public void deleteProduto(int cd_produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUTO, KEY_ID + " = ?",
				  new String[] { String.valueOf(cd_produto)});
        db.close();
    }


    public void deleteAllProduto() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUTO, null, null);
        db.close();
    }


    public int getProdutosCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PRODUTO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
	
}
