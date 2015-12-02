package com.econoom;

import android.app.*;
import android.os.*;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CadastroProduto extends Activity
{
	
	Spinner tp_cad;
	Spinner un_med_prod;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cadastro_produto);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.tp_cad, android.R.layout.simple_spinner_item);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		tp_cad = (Spinner) findViewById(R.id.tp_cad);
		
		tp_cad.setAdapter(adapter);
		
		un_med_prod = (Spinner) findViewById(R.id.un_med_prod);
		
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		R.array.un_med_prod, android.R.layout.simple_spinner_item);

		tp_cad.setAdapter(adapter);
		
		un_med_prod.setAdapter(adapter2);
	}
	
}
