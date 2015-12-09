package com.econoom;

import java.util.List;

import com.econoom.Banco.NotaValorDB;
import com.econoom.auxiliar.ListaProdutos;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.econoom.entidade.Produto;

public class ListaNotas extends Activity {
	
	private ListView listProdView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lista_notas);
        
        ListaProdutos adapter = new ListaProdutos(this,R.layout.list_lista_nota,buscaProduto());       
		
		listProdView = (ListView) findViewById(R.id.list_prod);
		
		listProdView.setAdapter(adapter);
        
    }
	
	public List<Produto> buscaProduto(){
    	
    	List<Produto> produtoList;
    	
    	NotaValorDB db = new NotaValorDB(this);
    	
    	produtoList = db.getTodasNotas();
    	
    	return produtoList;
    	
    }

}
