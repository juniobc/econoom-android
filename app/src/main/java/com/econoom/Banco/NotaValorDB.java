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
	
	private static final int DATABASE_VERSION = 2;

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
    private static final String KEY_DESC_NT = "desc_nota";
    private static final String KEY_DT_VENC = "dt_venc_nota";
    private static final String TAG = "NotaValorDB";

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
			+ KEY_LONG + " TEXT," + KEY_TP_CAD + " INTEGER," + KEY_DT_HR_CAD + " INTEGER, "
            +KEY_DESC_NT + " TEXT, "+KEY_DT_VENC+" TEXT )";

        db.execSQL(CREATE_PRODUTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE "+TABLE_PRODUTO+" ADD COLUMN "+KEY_DESC_NT+" TEXT");
            db.execSQL("ALTER TABLE "+TABLE_PRODUTO+" ADD COLUMN "+KEY_DT_VENC+" TEXT");
        }

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
        values.put(KEY_DESC_NT, servico.getDescNotaValor());

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
        values.put(KEY_DESC_NT, produto.getDescNotaValor());

        db.insert(TABLE_PRODUTO, null, values);
        db.close();
    }

    public double[] getGastoDebitoCredito() {
        
        String selectQuery = "SELECT "+KEY_TP_CAD+", SUM("+KEY_PRECO+") FROM " + TABLE_PRODUTO + " WHERE "+KEY_TP_CAD+" IN(0,1) "
        		+"GROUP BY "+KEY_TP_CAD;

        double[] somaCredDeb = new double[2];
        int cont = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
	        do {
                somaCredDeb[cont] = cursor.getDouble(1);
	        	cont++;
	        } while (cursor.moveToNext());
        }else{
            somaCredDeb = null;
        }

        return somaCredDeb;
    }

    public void gastoMes(){

        String selectQuery = "SELECT strftime('%m',"+KEY_DT_HR_CAD+"/ 1000, 'unixepoch') FROM " + TABLE_PRODUTO + " WHERE "+KEY_TP_CAD+" IN(0,1) and strftime('%m',"+KEY_DT_HR_CAD+"/ 1000, 'unixepoch') = '12'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            Log.d(TAG,cursor.getString(0));
        }else{
            Log.d(TAG,"nenhum resultado!");
        }

    }

    public double[] gastoIntervData(long dataInicial, long dataFinal, int tipoNota){

        String selectQuery = "SELECT "+KEY_TP_CAD+", SUM("+KEY_PRECO+") FROM " + TABLE_PRODUTO + " WHERE "+KEY_TP_CAD+" IN(0,1) "
                +" AND "+KEY_DT_HR_CAD+" BETWEEN "+dataInicial+" AND "+dataFinal + " AND "+KEY_TP_NT+ " = "+tipoNota
                +" GROUP BY "+KEY_TP_CAD;

        double[] somaCredDeb = new double[2];
        int cont = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                somaCredDeb[cont] = cursor.getDouble(1);
                cont++;
            } while (cursor.moveToNext());
        }else{
            somaCredDeb = null;
        }

        return somaCredDeb;
    }
    
    public List<NotaValor> getTodasNotas() {

    	List<NotaValor> notasValor = new ArrayList<NotaValor>();
        NotaValor notaValor;
        
        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_PRECO+", "+KEY_LAT+", "+KEY_LONG+","
        +KEY_TP_CAD+", "+KEY_TP_UN_PROD+", "+KEY_CD_BARRA+", "+KEY_QT_TP_UN+", "
        +KEY_QUANT+", "+KEY_DT_HR_CAD+", "+KEY_DESC_NT+" FROM " + TABLE_PRODUTO;
     
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        
        if (cursor.moveToFirst()) {
	        do {
                notaValor = new NotaValor(
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
	        		cursor.getLong(10),
                    cursor.getString(11)
	        	);
                notasValor.add(notaValor);
	        	
	        } while (cursor.moveToNext());
        }else{
            notasValor = null;
        }

        return notasValor;
    }

    public List<Produto> getTodosProdutos() {

        List<Produto> produtos = new ArrayList<Produto>();
        Produto produto;

        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_PRECO+", "+KEY_LAT+", "+KEY_LONG+","
                +KEY_TP_CAD+", "+KEY_TP_UN_PROD+", "+KEY_CD_BARRA+", "+KEY_QT_TP_UN+", "
                +KEY_QUANT+", "+KEY_DT_HR_CAD+", "+KEY_DESC_NT+" FROM " + TABLE_PRODUTO
                +" WHERE "+KEY_TP_NT+" = 0 ORDER BY "+KEY_ID+" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                produto = new Produto(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_NM_PROD)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_PRECO)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LAT)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LONG)),
                        "",
                        cursor.getInt(cursor.getColumnIndex(KEY_TP_CAD)),
                        cursor.getString(cursor.getColumnIndex(KEY_TP_UN_PROD)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_CD_BARRA)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_QT_TP_UN)),
                        cursor.getInt(cursor.getColumnIndex(KEY_QUANT)),
                        cursor.getLong(cursor.getColumnIndex(KEY_DT_HR_CAD)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESC_NT))
                );
                produtos.add(produto);

            } while (cursor.moveToNext());
        }else{
            produtos = null;
        }

        return produtos;
    }

    public List<Conta> getTodasContas() {

        List<Conta> contas = new ArrayList<Conta>();
        Conta conta;

        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_PRECO+ ", " +KEY_TP_CAD+ ", "+KEY_DT_HR_CAD+" FROM " + TABLE_PRODUTO
                +" WHERE "+KEY_TP_NT+" = 1 ORDER BY "+ KEY_ID+" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                conta = new Conta(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_NM_PROD)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_PRECO)),
                        "",
                        cursor.getInt(cursor.getColumnIndex(KEY_TP_CAD)),
                        cursor.getLong(cursor.getColumnIndex(KEY_DT_HR_CAD))
                );
                contas.add(conta);

            } while (cursor.moveToNext());
        }else{
            contas = null;
        }

        return contas;
    }

    public List<Servico> getTodosServicos() {

        List<Servico> servicos = new ArrayList<Servico>();
        Servico servico;

        String selectQuery = "SELECT "+KEY_ID+", "+KEY_NM_PROD+", "+KEY_PRECO
                +", "+KEY_LAT+", "+KEY_LONG+", " +KEY_TP_CAD+ ", "
                +KEY_DT_HR_CAD+", "+KEY_DESC_NT+" FROM " + TABLE_PRODUTO
                +" WHERE "+KEY_TP_NT+" = 2 ORDER BY "+ KEY_ID+" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                servico = new Servico(
                        cursor.getInt(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_NM_PROD)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_PRECO)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_LAT)),
                        cursor.getFloat(cursor.getColumnIndex(KEY_LONG)),
                        "",
                        cursor.getInt(cursor.getColumnIndex(KEY_TP_CAD)),
                        cursor.getLong(cursor.getColumnIndex(KEY_DT_HR_CAD)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESC_NT))
                );
                servicos.add(servico);

            } while (cursor.moveToNext());
        }else{
            servicos = null;
        }

        return servicos;
    }

    public int updateNotaValor(NotaValor notaValor, int tpNotaValor){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NM_PROD, notaValor.getNome());
        values.put(KEY_PRECO, notaValor.getValor());
        values.put(KEY_TP_NT, tpNotaValor);
        if(notaValor.getLatitude() != 0)
            values.put(KEY_LAT, notaValor.getLatitude());
        else
            values.putNull(KEY_LAT);
        if(notaValor.getLongitude() != 0)
            values.put(KEY_LONG, notaValor.getLongitude());
        else
            values.putNull(KEY_LONG);
        values.put(KEY_TP_CAD, notaValor.getTpPagamento());
        if(notaValor.getTpUnidadeMedida() != null)
            if(!notaValor.getTpUnidadeMedida().equals(""))
                values.put(KEY_TP_UN_PROD, notaValor.getTpUnidadeMedida());
        else
            values.putNull(KEY_TP_UN_PROD);
        if(notaValor.getCodigoBarras() != 0)
            values.put(KEY_CD_BARRA, notaValor.getCodigoBarras());
        else
            values.putNull(KEY_CD_BARRA);
        if(notaValor.getQtUnMedida() != 0)
            values.put(KEY_QT_TP_UN, notaValor.getQtUnMedida());
        else
            values.putNull(KEY_QT_TP_UN);
        if(notaValor.getQuantidade() != 0)
            values.put(KEY_QUANT, notaValor.getQuantidade());
        else
            values.putNull(KEY_QUANT);
        if(notaValor.getQtUnMedida() != 0)
            values.put(KEY_QT_TP_UN, notaValor.getQtUnMedida());
        else
            values.putNull(KEY_QT_TP_UN);
        if(notaValor.getDescNotaValor() != null)
            if(!notaValor.getDescNotaValor().equals(""))
                values.put(KEY_DESC_NT, notaValor.getDescNotaValor());
        else
            values.putNull(KEY_DESC_NT);

        return db.update(TABLE_PRODUTO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(notaValor.getId()) });

    }


    /*public void deleteProduto(int cd_produto) {
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
