package com.econoom;

import android.app.*;
import android.os.*;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CadastroProduto extends Activity
{
	
	Spinner tp_cad;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.cadastro_produto);
		
		tp_cad = (Spinner) findViewById(R.id.tp_cad);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.tp_cad, android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		tp_cad.setAdapter(adapter);
		
	}
	
}
