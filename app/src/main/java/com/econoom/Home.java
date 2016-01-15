package com.econoom;

import com.econoom.Banco.NotaValorDB;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.TextView;

public class Home extends Activity {
	
	public static final String TAG = "Home";
	private TextView txt_gasto_total;
	private double gastoTotal;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        
        calculaGasto();
        
    }
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first

	    calculaGasto();

	}
	
	public void listaNotas(View v){
		
		Intent i = new Intent(Home.this, ListaNotas.class);
		startActivity(i);
		
	}
	
	public void cadastraProduto(View v){
		//Log.d(TAG,"cadastraProduto");
		Intent i = new Intent(Home.this, CadastroProduto.class);
		startActivity(i);
		
	}
	
	public void calculaGasto(){
		
		NotaValorDB db = new NotaValorDB(this);
        
        txt_gasto_total = (TextView) findViewById(R.id.txt_gasto_total);

        //db.gastoMes();
        
        if(db.getGastoDebitoCredito() != null){
        	gastoTotal = db.getGastoDebitoCredito()[0] + db.getGastoDebitoCredito()[1];
        	txt_gasto_total.setText("R$ "+String.format("%.2f",gastoTotal).replace(".",","));
        }else
        	txt_gasto_total.setText("0,00");
		
	}

}