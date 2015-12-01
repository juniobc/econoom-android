package com.econoom;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class Home extends Activity {
	
	public static final String TAG = "Home";
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        
    }
	
	public void cadastraProduto(View v){
		//Log.d(TAG,"cadastraProduto");
		Intent i = new Intent(Home.this, CadastroProduto.class);
		startActivity(i);
		
	}

}