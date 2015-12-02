package com.econoom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.util.Log;
import android.view.View;

import com.econoom.entidade.Produto;

public class CadastroProduto extends Activity
{
	public static final String TAG = "cadastroProduto";
	private Spinner tp_cad_prod;
	private Spinner un_med_prod;
	private Spinner tp_pag_prod;
	private ArrayAdapter<CharSequence> adapter;
	private EditText qt_un_prod;
	private EditText qt_prod;
	private EditText cd_barra_prod;
	private EditText end_prod;
	private EditText nm_cad;
	private EditText vl_prod;
	private EditText dt_validade;
	private Produto produto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cadastro_produto);
		
		tp_cad_prod = (Spinner) findViewById(R.id.tp_cad_prod);
		nm_cad = (EditText) findViewById(R.id.nm_cad);
		un_med_prod = (Spinner) findViewById(R.id.un_med_prod);		
		qt_un_prod = (EditText) findViewById(R.id.qt_un_prod);
		qt_prod = (EditText) findViewById(R.id.qt_prod);
		vl_prod = (EditText) findViewById(R.id.vl_prod);
		cd_barra_prod = (EditText) findViewById(R.id.cd_barra_prod);		
		end_prod = (EditText) findViewById(R.id.end_prod);
		dt_validade = (EditText) findViewById(R.id.dt_validade);
		tp_pag_prod = (Spinner) findViewById(R.id.tp_pag_prod);
		
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		adapter = ArrayAdapter.createFromResource(this,
		R.array.tp_cad_prod, android.R.layout.simple_spinner_item);
				
		tp_cad_prod.setAdapter(adapter);
		
		adapter = ArrayAdapter.createFromResource(this,
		R.array.un_med_prod, android.R.layout.simple_spinner_item);
		
		un_med_prod.setAdapter(adapter);
		
		adapter = ArrayAdapter.createFromResource(this,
		R.array.tp_pag_prod, android.R.layout.simple_spinner_item);		
		
		tp_pag_prod.setAdapter(adapter);
		
		tp_cad_prod.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				if(position == 1 || position == 2){					
					qt_un_prod.setVisibility(View.GONE);		
					un_med_prod.setVisibility(View.GONE);
					qt_prod.setVisibility(View.GONE);
					cd_barra_prod.setVisibility(View.GONE);
					if(position == 1)
						end_prod.setVisibility(View.GONE);
					else
						end_prod.setVisibility(View.VISIBLE);
				}else{
					qt_un_prod.setVisibility(View.VISIBLE);
					un_med_prod.setVisibility(View.VISIBLE);
					qt_prod.setVisibility(View.VISIBLE);
					cd_barra_prod.setVisibility(View.VISIBLE);
					end_prod.setVisibility(View.VISIBLE);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
	}
	
	public void cadastrar(View v){
		
		Log.d(TAG,"cadastrar");
		
		String nome = nm_cad.getText().toString();
		float valor = Float.parseFloat((vl_prod.getText().toString().replace(",",".")));
		double latitude = 16.55555554487;
		double longitude = 16.55555554487;
		String dataValidade = "20150101";
		//int tpPagamento = Int.parseInt(tp_pag_prod.getSelectedItem().toString());;
		
		//produto = new Produto();
		
	}
	
}
