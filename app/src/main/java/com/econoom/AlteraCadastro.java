package com.econoom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.econoom.entidade.Conta;
import com.econoom.entidade.NotaValor;
import com.econoom.entidade.Produto;
import com.econoom.entidade.Servico;

import java.text.NumberFormat;

/**
 * Created by m1031007 on 19/01/2016.
 */
public class AlteraCadastro extends Activity {

    public static final String OBJETO_PRODUTO = "com.econoom.ListaProduto.produto";
    public static final String OBJETO_CONTA = "com.econoom.ListaProduto.conta";
    public static final String OBJETO_SERVICO = "com.econoom.ListaProduto.servico";
    private static final String TAG = "AlteraCadastro";
    private NotaValor notaValor;
    private Intent intent;

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
    private EditText desc_nota_valor;
    private Produto produto;
    private Conta conta;
    private Servico servico;
    private Double latitude;
    private Double longitude;

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

        intent = getIntent();

        if(intent.hasExtra(OBJETO_PRODUTO)){
            notaValor = (NotaValor) intent.getExtras().getSerializable(OBJETO_PRODUTO);
        }
        else if(intent.hasExtra(OBJETO_CONTA)){
            notaValor = (NotaValor) intent.getExtras().getSerializable(OBJETO_CONTA);
        }

        adapter = ArrayAdapter.createFromResource(this,
                R.array.tp_cad_prod, android.R.layout.simple_spinner_item);

        tp_cad_prod.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.un_med_prod, android.R.layout.simple_spinner_item);

        un_med_prod.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this,
                R.array.tp_pag_prod, android.R.layout.simple_spinner_item);

        tp_pag_prod.setAdapter(adapter);

        tp_cad_prod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 1 || position == 2){
                    qt_un_prod.setVisibility(View.GONE);
                    un_med_prod.setVisibility(View.GONE);
                    qt_prod.setVisibility(View.GONE);
                    cd_barra_prod.setVisibility(View.GONE);
                    if(position == 1) {
                        end_prod.setVisibility(View.GONE);
                        desc_nota_valor.setVisibility(View.GONE);
                    }else{
                        end_prod.setVisibility(View.VISIBLE);
                        desc_nota_valor.setVisibility(View.VISIBLE);
                    }
                }else{
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
                    str = nf.format(Double.parseDouble(str) / 100).replace("R$","");
                    if(str.equals("0,00"))
                        str = "";
                    vl_prod.setText(str);
                    vl_prod.setSelection(vl_prod.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
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
                    str = nf.format(Double.parseDouble(str) / 100).replace("R$","");
                    if(str.equals("0,00"))
                        str = "";
                    qt_un_prod.setText(str);
                    qt_un_prod.setSelection(qt_un_prod.getText().length());
                } catch (NumberFormatException e) {
                    s = "";
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        end_prod.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(AlteraCadastro.this, MapaEndereco.class);
                if(!end_prod.getText().toString().equals("")){
                    i.putExtra(MapaEndereco.RESPOSTA_LATITUDE,
                            Double.parseDouble(end_prod.getText().toString().split(",")[0]));

                    i.putExtra(MapaEndereco.RESPOSTA_LONGITUDE,
                            Double.parseDouble(end_prod.getText().toString().split(",")[1]));
                }
                startActivityForResult(i, 0);

            }

        });

    }

}
