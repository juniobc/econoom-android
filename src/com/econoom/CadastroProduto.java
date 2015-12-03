package com.econoom;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
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
		
		vl_prod.setInputType(InputType.TYPE_CLASS_NUMBER);
		vl_prod.addTextChangedListener(new TextWatcher() {
 
            private boolean isUpdating = false;
                        // Pega a formatacao do sistema, se for brasil R$ se EUA US$
            private NumberFormat nf = NumberFormat.getCurrencyInstance();
 
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int after) {
                // Evita que o método seja executado varias vezes.
                                // Se tirar ele entre em loop
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }
 
                isUpdating = true;
                String str = s.toString();
                // Verifica se já existe a máscara no texto.
                boolean hasMask = ((str.indexOf(".") > -1 || str.indexOf(",") > -1));
                                // Verificamos se existe máscara
                if (hasMask) {
                    // Retiramos a máscara.
                    str = str.replaceAll("[,]", "").replaceAll("[.]", "");
                }
 
                try {
                    // Transformamos o número que está escrito no EditText em
                    // monetário.
                    str = nf.format(Double.parseDouble(str) / 100);
                    str = str.replace("R$","");
                    vl_prod.setText(str);
                    vl_prod.setSelection(vl_prod.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }
 
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {
                // Não utilizamos
            }
 
            @Override
            public void afterTextChanged(Editable s) {
                // Não utilizamos
            }
        });
		
	}
	
	public void cadastrar(View v){
		
		Log.d(TAG,"cadastrar");
		
		String nome;
		float valor;
		double latitude;
		double longitude;
		String dataValidade;
		int tpPagamento;
		String tpUnidadeMedida;
		double codigoBarras;
		float qtUnMedida;
		int quantidade;
		
		nome = nm_cad.getText().toString();
		if(vl_prod.getText().toString().equals(""))
			valor = 0;
		else
			valor = Float.parseFloat((vl_prod.getText().toString().replace(",",".")));
		latitude = 16.55555554487;
		longitude = 16.55555554487;
		dataValidade = "20150101";
		tpPagamento = tp_pag_prod.getSelectedItemPosition();
		tpUnidadeMedida = tp_pag_prod.getSelectedItem().toString();
		if(cd_barra_prod.getText().toString().equals(""))
			codigoBarras = 0;
		else
			codigoBarras = Double.parseDouble(cd_barra_prod.getText().toString());
		if(qt_un_prod.getText().toString().equals(""))
			qtUnMedida = 0;
		else
			qtUnMedida = Float.parseFloat((qt_un_prod.getText().toString().replace("R$","").replace(",",".")));
		if(qt_prod.getText().toString().equals(""))
			quantidade = 0;
		else
			quantidade = Integer.parseInt(qt_prod.getText().toString());
		
		produto = new Produto(nome,valor,latitude,longitude,dataValidade,tpPagamento,
		tpUnidadeMedida,codigoBarras,qtUnMedida,quantidade);
		
		Toast.makeText(this, produto.getNome(), Toast.LENGTH_LONG).show();
		Toast.makeText(this, Float.toString(produto.getValor()), Toast.LENGTH_LONG).show();
		Toast.makeText(this, Integer.toString(produto.getTpPagamento()), Toast.LENGTH_LONG).show();
		Toast.makeText(this, produto.getTpUnidadeMedida(), Toast.LENGTH_LONG).show();
		Toast.makeText(this, Double.toString(produto.getCodigoBarras()), Toast.LENGTH_LONG).show();
		Toast.makeText(this, Float.toString(produto.getQtUnMedida()), Toast.LENGTH_LONG).show();
		Toast.makeText(this, Integer.toString(produto.getQuantidade()), Toast.LENGTH_LONG).show();
		
		
	}
	
}
