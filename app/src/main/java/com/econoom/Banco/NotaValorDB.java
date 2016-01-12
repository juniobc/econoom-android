package com.econoom.Banco;

import java.util.ArrayList;
import java.util.List;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import android.util.Log;

import com.econoom.entidade.*;

public class NotaValorDB extends SQLiteOpenHelper
{
	
	private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "econoom";

    private static final String TABLE_PRODUTO = "produtos";

    private static final String KEY_ID = "cd_produto";
	private static final String KEY_TP_NT = "tp_nota";
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

    public NotaValorDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUTOS_TABLE = "CREATE TABLE " + TABLE_PRODUTO + "("
        	+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TP_NT + " INTEGER," + KEY_CD_BARRA + " REAL,"
        	+ KEY_NM_PROD + " TEXT," + KEY_TP_UN_PROD + " TEXT," + KEY_QT_TP_UN + " REAL,"
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
    
    public void addConta(Conta conta) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, conta.getNome());
        values.put(KEY_PRECO, Float.toString((conta.getValor())));
        values.put(KEY_TP_NT, 1);
		values.put(KEY_TP_CAD, conta.getTpPagamento());
		values.put(KEY_DT_HR_CAD, System.currentTimeMillis()); 

        db.insert(TABLE_PRODUTO, null, values);
        db.close();
    }
    
    public void addServico(Servico servico) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, servico.getNome());
        values.put(KEY_PRECO, Float.toString((servico.getValor())));
        values.put(KEY_TP_NT, 2);
		values.put(KEY_LAT, servico.getLatitude());
		values.put(KEY_LONG, servico.getLongitude());
		values.put(KEY_TP_CAD, servico.getTpPagamento());
		values.put(KEY_DT_HR_CAD, System.currentTimeMillis()); 

        db.insert(TABLE_PRODUTO, null, values);
        db.close();
    }

    public void addProduto(Produto produto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, produto.getNome());
        values.put(KEY_TP_UN_PROD, produto.getTpUnidadeMedida());
        values.put(KEY_QT_TP_UN, produto.getQtUnMedida());
        values.put(KEY_PRECO, Float.toString((produto.getValor())));
        values.put(KEY_QUANT, produto.getQuantidade());
        values.put(KEY_CD_BARRA, produto.getCodigoBarras());
        values.put(KEY_TP_NT, 0);
		values.put(KEY_LAT, produto.getLatitude());
		values.put(KEY_LONG, produto.getLongitude());
		values.put(KEY_TP_CAD, produto.getTpPagamento());
		values.put(KEY_DT_HR_CAD, System.currentTimeMillis()); 

        db.insert(TABLE_PRODUTO, null, values);
        db.close();
    }

    /*NotaServicoDB getProduto(int nm_prod) {
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
    }*/

    public double[] getGastoTotal() {

    	double[] tp_gasto = new double[2];
    	int cont = 0;
        
        String selectQuery = "SELECT "+KEY_TP_CAD+", SUM("+KEY_PRECO+") FROM " + TABLE_PRODUTO + " WHERE "+KEY_TP_CAD+" IN(0,1) "
        		+"GROUP BY "+KEY_TP_CAD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
	        do {
	        	tp_gasto[cont] = cursor.getDouble(1);
	        	cont++;
	        } while (cursor.moveToNext());
        }else{
        	tp_gasto = null;
        }

        if(tp_gasto != null){
	        Log.d("getGastoTotal","valor1: "+tp_gasto[0]);
	        Log.d("getGastoTotal","valor2: "+tp_gasto[1]);
        }else
        	Log.d("getGastoTotal","valor nulo");

        return tp_gasto;
    }
    
    public List<Produto> getTodasNotas() {

    	List<Produto> produtos = new ArrayList<Produto>();
    	Produto produto;
        
        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_PRECO+", "+KEY_LAT+", "+KEY_LONG+","
        +KEY_TP_CAD+", "+KEY_TP_UN_PROD+", "+KEY_CD_BARRA+", "+KEY_QT_TP_UN+", "+KEY_QUANT+", "+KEY_DT_HR_CAD+" FROM " + TABLE_PRODUTO;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
	        do {
	        	produto = new Produto(
	        		cursor.getInt(0),
	        		cursor.getString(1),
	        		cursor.getFloat(2),
	        		cursor.getDouble(3),
	        		cursor.getDouble(4),
	        		"",
	        		cursor.getInt(6),
	        		cursor.getString(5),
	        		cursor.getDouble(7),
	        		cursor.getFloat(8),
	        		cursor.getInt(9),
	        		cursor.getString(10)
	        	);
	        	produtos.add(produto);
	        	
	        } while (cursor.moveToNext());
        }else{
        	produtos = null;
        }

        return produtos;
    }


    /*public int updateProduto(NotaServicoDB produto) {
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
    }*/
	
}
