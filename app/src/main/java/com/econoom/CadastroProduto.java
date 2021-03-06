package com.econoom;

import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;

import com.econoom.Banco.NotaValorDB;
import com.econoom.entidade.Conta;
import com.econoom.entidade.NotaValor;
import com.econoom.entidade.Produto;
import com.econoom.entidade.Servico;

import java.util.*;

public class CadastroProduto extends Activity{

    public static final String OBJETO_PRODUTO = "com.econoom.ListaProduto.produto";
    public static final String OBJETO_CONTA = "com.econoom.ListaProduto.conta";
    public static final String OBJETO_SERVICO = "com.econoom.ListaProduto.servico";
    private Intent intent;
    private NotaValor notaValor;

	public static final String TAG = "CadastroProduto";
	private Spinner tp_cad_prod;
	private Spinner un_med_prod;
	private Spinner tp_pag_prod;
	private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter_un_medida;
	private EditText qt_un_prod;
	private EditText qt_prod;
	private EditText cd_barra_prod;
	private EditText end_prod;
	private EditText nm_cad;
	private EditText vl_prod;
	private EditText dt_validade;
	private EditText desc_nota_valor;
	private Produto produto;
	private Conta conta;
	private Servico servico;
	private Double latitude;
	private Double longitude;
    private Button btn_altera_prod;
    private Button btn_cad_prod;
    private NumberFormat nf = NumberFormat.getCurrencyInstance();
    private EditText cd_nota_valor;
	
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
		desc_nota_valor = (EditText) findViewById(R.id.desc_nota_valor);
        btn_altera_prod = (Button) findViewById(R.id.btn_altera_prod);
        btn_cad_prod = (Button) findViewById(R.id.btn_cad_prod);
        cd_nota_valor = (EditText) findViewById(R.id.cd_nota_valor);
		
		//end_prod.setKeyListener(null); 
		//end_prod.setEnabled(false);
				
		//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        btn_altera_prod.setVisibility(View.GONE);
        cd_nota_valor.setVisibility(View.GONE);
		
		adapter = ArrayAdapter.createFromResource(this,
		R.array.tp_cad_prod, android.R.layout.simple_spinner_item);
				
		tp_cad_prod.setAdapter(adapter);
		
		adapter_un_medida = ArrayAdapter.createFromResource(this,
		R.array.un_med_prod, android.R.layout.simple_spinner_item);
		
		un_med_prod.setAdapter(adapter_un_medida);
		
		adapter = ArrayAdapter.createFromResource(this,
		R.array.tp_pag_prod, android.R.layout.simple_spinner_item);		
		
		tp_pag_prod.setAdapter(adapter);
		
		tp_cad_prod.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 1 || position == 2) {
                    qt_un_prod.setVisibility(View.GONE);
                    un_med_prod.setVisibility(View.GONE);
                    qt_prod.setVisibility(View.GONE);
                    cd_barra_prod.setVisibility(View.GONE);
                    if (position == 1) {
                        end_prod.setVisibility(View.GONE);
                        desc_nota_valor.setVisibility(View.GONE);
                    } else {
                        end_prod.setVisibility(View.VISIBLE);
                        desc_nota_valor.setVisibility(View.VISIBLE);
                    }
                } else {
                    qt_un_prod.setVisibility(View.VISIBLE);
                    un_med_prod.setVisibility(View.VISIBLE);
                    qt_prod.setVisibility(View.VISIBLE);
                    cd_barra_prod.setVisibility(View.VISIBLE);
                    end_prod.setVisibility(View.VISIBLE);
                    desc_nota_valor.setVisibility(View.VISIBLE);
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

            private NumberFormat nf = NumberFormat.getCurrencyInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {

                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;
                String str = s.toString();

                boolean hasMask = ((str.indexOf(".") > -1 || str.indexOf(",") > -1));

                if (hasMask) {
                    str = str.replaceAll("[,]", "").replaceAll("[.]", "");
                }

                try {
                    str = nf.format(Double.parseDouble(str) / 100).replace("R$", "");
                    if (str.equals("0,00"))
                        str = "";
                    vl_prod.setText(str);
                    vl_prod.setSelection(vl_prod.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
		
		qt_un_prod.setInputType(InputType.TYPE_CLASS_NUMBER);
		qt_un_prod.addTextChangedListener(new TextWatcher() {

            private boolean isUpdating = false;

            private NumberFormat nf = NumberFormat.getCurrencyInstance();

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {

                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                isUpdating = true;
                String str = s.toString();

                boolean hasMask = ((str.indexOf(".") > -1 || str.indexOf(",") > -1));

                if (hasMask) {
                    str = str.replaceAll("[,]", "").replaceAll("[.]", "");
                }

                try {
                    str = nf.format(Double.parseDouble(str) / 100).replace("R$", "");
                    if (str.equals("0,00"))
                        str = "";
                    qt_un_prod.setText(str);
                    qt_un_prod.setSelection(qt_un_prod.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
		
		end_prod.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(CadastroProduto.this, MapaEndereco.class);
                if (!end_prod.getText().toString().equals("")) {
                    i.putExtra(MapaEndereco.RESPOSTA_LATITUDE,
                            Double.parseDouble(end_prod.getText().toString().split(",")[0]));

                    i.putExtra(MapaEndereco.RESPOSTA_LONGITUDE,
                            Double.parseDouble(end_prod.getText().toString().split(",")[1]));
                }
                startActivityForResult(i, 0);

            }

        });

        carregaNota();
		
	}

    public void carregaNota(){

        intent = getIntent();

        if(intent.hasExtra(OBJETO_PRODUTO)){

            btn_altera_prod.setVisibility(View.VISIBLE);
            btn_cad_prod.setVisibility(View.GONE);
            notaValor = (NotaValor) intent.getExtras().getSerializable(OBJETO_PRODUTO);

            tp_cad_prod.setSelection(0);

            nm_cad.setText(notaValor.getNome());
            desc_nota_valor.setText(notaValor.getDescNotaValor());
            qt_un_prod.setText(nf.format(notaValor.getQtUnMedida()).replace("R$", ""));
            un_med_prod.setSelection(adapter_un_medida.getPosition(notaValor.getTpUnidadeMedida()));
            qt_prod.setText(String.format("%d", notaValor.getQuantidade()));
            vl_prod.setText(nf.format(notaValor.getValor()).replace("R$", ""));
            if(notaValor.getCodigoBarras() != 0)
                cd_barra_prod.setText(String.format("%.0f", notaValor.getCodigoBarras()));
            end_prod.setText(String.format("%.13f", notaValor.getLatitude()).replace(",", ".") + ","
                    + String.format("%.13f", notaValor.getLongitude()).replace(",", "."));
            dt_validade.setText(notaValor.getDataValidade());
            tp_pag_prod.setSelection(notaValor.getTpPagamento());

        }else if(intent.hasExtra(OBJETO_CONTA)){

            btn_altera_prod.setVisibility(View.VISIBLE);
            btn_cad_prod.setVisibility(View.GONE);
            notaValor = (NotaValor) intent.getExtras().getSerializable(OBJETO_CONTA);

            tp_cad_prod.setSelection(1);

            nm_cad.setText(notaValor.getNome());
            //desc_nota_valor.setText(notaValor.getDescNotaValor());
            vl_prod.setText(nf.format(notaValor.getValor()).replace("R$", ""));
            dt_validade.setText(notaValor.getDataValidade());
            tp_pag_prod.setSelection(notaValor.getTpPagamento());

        }else if(intent.hasExtra(OBJETO_SERVICO)){

            btn_altera_prod.setVisibility(View.VISIBLE);
            btn_cad_prod.setVisibility(View.GONE);
            notaValor = (NotaValor) intent.getExtras().getSerializable(OBJETO_SERVICO);

            tp_cad_prod.setSelection(2);

            nm_cad.setText(notaValor.getNome());
            desc_nota_valor.setText(notaValor.getDescNotaValor());
            vl_prod.setText(nf.format(notaValor.getValor()).replace("R$", ""));
            end_prod.setText(String.format("%f", notaValor.getLatitude()) + ","
                    + String.format("%f", notaValor.getLongitude()));
            dt_validade.setText(notaValor.getDataValidade());
            tp_pag_prod.setSelection(notaValor.getTpPagamento());

        }

    }
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK) {
			
		    if (data == null) {
		    	return;
		    }
		    
		    this.latitude = data.getDoubleExtra(MapaEndereco.RESPOSTA_LATITUDE, 0);
		    this.longitude = data.getDoubleExtra(MapaEndereco.RESPOSTA_LONGITUDE, 0);
		    
		    end_prod.setText(Double.toString(latitude)+","+Double.toString(longitude));
	    
		}
		
    }

    private void setAnswerShownResult() {

        setResult(RESULT_OK);
        finish();

    }

    public void alterar(View v){

        if(consisteCampos()){

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
            String descNotaValor;

            NotaValorDB db = new NotaValorDB(this);

            nome = nm_cad.getText().toString().toUpperCase(Locale.getDefault());

            if(vl_prod.getText().toString().equals(""))
                valor = 0;
            else
                valor = Float.parseFloat((vl_prod.getText().toString().replace(".","").replace(",",".")));

            dataValidade = dt_validade.getText().toString().toUpperCase(Locale.getDefault());

            tpPagamento = tp_pag_prod.getSelectedItemPosition();

            if(tp_cad_prod.getSelectedItemPosition() == 1){

                conta = new Conta(notaValor.getId(), nome, valor, dataValidade, tpPagamento, 0);

                db.updateNotaValor(conta, 1);

                Toast.makeText(this, "Conta alterada com sucesso!", Toast.LENGTH_LONG).show();

            }

            if(tp_cad_prod.getSelectedItemPosition() == 2){

                descNotaValor = desc_nota_valor.getText().toString();

                latitude = Double.parseDouble(end_prod.getText().toString().split(",")[0]);
                longitude = Double.parseDouble(end_prod.getText().toString().split(",")[1]);

                servico = new Servico(notaValor.getId(), nome, valor, latitude, longitude, dataValidade, tpPagamento, 0, descNotaValor);

                db.updateNotaValor(servico, 2);

                Toast.makeText(this, "Servico alterado com sucesso!", Toast.LENGTH_LONG).show();

            }

            if(tp_cad_prod.getSelectedItemPosition() == 0){

                descNotaValor = desc_nota_valor.getText().toString();

                latitude = Double.parseDouble(end_prod.getText().toString().split(",")[0]);
                longitude = Double.parseDouble(end_prod.getText().toString().split(",")[1]);

                tpUnidadeMedida = un_med_prod.getSelectedItem().toString().toUpperCase(Locale.getDefault());
                if(cd_barra_prod.getText().toString().equals(""))
                    codigoBarras = 0;
                else
                    codigoBarras = Double.parseDouble(cd_barra_prod.getText().toString());
                if(qt_un_prod.getText().toString().equals(""))
                    qtUnMedida = 0;
                else
                    qtUnMedida = Float.parseFloat((qt_un_prod.getText().toString().replace(".","").replace(",",".")));
                if(qt_prod.getText().toString().equals(""))
                    quantidade = 0;
                else
                    quantidade = Integer.parseInt(qt_prod.getText().toString());

                produto = new Produto(notaValor.getId(), nome,valor,latitude,longitude,dataValidade,tpPagamento,
                        tpUnidadeMedida,codigoBarras,qtUnMedida,quantidade, 0, descNotaValor);

                db.updateNotaValor(produto, 0);

                Toast.makeText(this, "Produto alterado com sucesso!", Toast.LENGTH_LONG).show();

            }

            setAnswerShownResult();

        }

    }
	
	public void cadastrar(View v){

		if(consisteCampos()){
		
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
			String descNotaValor;
			
			NotaValorDB db = new NotaValorDB(this);
			
			nome = nm_cad.getText().toString().toUpperCase(Locale.getDefault());

			if(vl_prod.getText().toString().equals(""))
				valor = 0;
			else
				valor = Float.parseFloat((vl_prod.getText().toString().replace(".","").replace(",",".")));
			
			dataValidade = dt_validade.getText().toString().toUpperCase(Locale.getDefault());
			
			tpPagamento = tp_pag_prod.getSelectedItemPosition();
			
			if(tp_cad_prod.getSelectedItemPosition() == 1){
				
				conta = new Conta(0, nome, valor, dataValidade, tpPagamento, 0);
				
				db.addConta(conta);
				
				Toast.makeText(this, "Conta cadastrada com sucesso!", Toast.LENGTH_LONG).show();
				
			}
			
			if(tp_cad_prod.getSelectedItemPosition() == 2){

				descNotaValor = desc_nota_valor.getText().toString().toUpperCase(Locale.getDefault());
				
				latitude = this.latitude;
				longitude = this.longitude;
				
				servico = new Servico(0, nome, valor, latitude, longitude, dataValidade, tpPagamento, 0, descNotaValor);
				
				db.addServico(servico);
				
				Toast.makeText(this, "Servico cadastrado com sucesso!", Toast.LENGTH_LONG).show();
				
			}
			
			if(tp_cad_prod.getSelectedItemPosition() == 0){

				descNotaValor = desc_nota_valor.getText().toString().toUpperCase(Locale.getDefault());
				
				latitude = this.latitude;
				longitude = this.longitude;
			
				tpUnidadeMedida = un_med_prod.getSelectedItem().toString().toUpperCase(Locale.getDefault());
				if(cd_barra_prod.getText().toString().equals(""))
					codigoBarras = 0;
				else
					codigoBarras = Double.parseDouble(cd_barra_prod.getText().toString());
				if(qt_un_prod.getText().toString().equals(""))
					qtUnMedida = 0;
				else
					qtUnMedida = Float.parseFloat((qt_un_prod.getText().toString().replace(".","").replace(",",".")));
				if(qt_prod.getText().toString().equals(""))
					quantidade = 0;
				else
					quantidade = Integer.parseInt(qt_prod.getText().toString());
				
				produto = new Produto(0, nome,valor,latitude,longitude,dataValidade,tpPagamento,
				tpUnidadeMedida,codigoBarras,qtUnMedida,quantidade, 0, descNotaValor);
				
				db.addProduto(produto);
				
				Toast.makeText(this, "Produto cadastrado com sucesso!", Toast.LENGTH_LONG).show();
			
			}
			
			limpa_campos();
			
		}
		
	}
	
	public boolean consisteCampos(){
		
		if(nm_cad.getText().toString().equals("")){			
			Toast.makeText(this, "Informe o nome!", Toast.LENGTH_LONG).show();
			return false;			
		}
		
		if(vl_prod.getText().toString().equals("")){			
			Toast.makeText(this, "Informe o valor!", Toast.LENGTH_LONG).show();
			return false;			
		}
		
		if(tp_cad_prod.getSelectedItemPosition() == 1){
		
			if(dt_validade.getText().toString().equals("")){			
				Toast.makeText(this, "Informe a data de validade!", Toast.LENGTH_LONG).show();
				return false;			
			}
		
		}
		
		if(tp_cad_prod.getSelectedItemPosition() == 0){
			
			if(qt_un_prod.getText().toString().equals("")){			
				Toast.makeText(this, "Informe a quantidade da unidade de medida!", Toast.LENGTH_LONG).show();
				return false;			
			}
			
			if(qt_prod.getText().toString().equals("")){			
				Toast.makeText(this, "Informe a quantidade!", Toast.LENGTH_LONG).show();
				return false;			
			}
			
			if(end_prod.getText().toString().equals("")){			
				Toast.makeText(this, "Informe o endereço!", Toast.LENGTH_LONG).show();
				return false;			
			}
			
		}
		
		if(tp_cad_prod.getSelectedItemPosition() == 2){
			
			if(end_prod.getText().toString().equals("")){			
				Toast.makeText(this, "Informe o endereço!", Toast.LENGTH_LONG).show();
				return false;			
			}
			
		}
			
		
		return true;
		
	}
	
	public void limpa_campos(){
		
		tp_cad_prod.setSelection(0);
		nm_cad.setText("");
		un_med_prod.setSelection(0);		
		qt_un_prod.setText("");
		qt_prod.setText("");
		vl_prod.setText("");
		cd_barra_prod.setText("");		
		end_prod.setText("");
		dt_validade.setText("");
		tp_pag_prod.setSelection(0);
		desc_nota_valor.setText("");
		
	}
	
}
