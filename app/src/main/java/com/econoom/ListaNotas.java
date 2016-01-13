package com.econoom;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ListaNotas extends AppCompatActivity {
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_notas);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
 
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Produtos"));
        tabLayout.addTab(tabLayout.newTab().setText("Contas"));
        tabLayout.addTab(tabLayout.newTab().setText("Serviços"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
 
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new NotasPager (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
 
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
 
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
	
	/*private ListView listProdView;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lista_notas);
        
        ListaNotasValor adapter = new ListaNotasValor(this,R.layout.list_lista_produto,buscaProduto());
		
		listProdView = (ListView) findViewById(R.id.list_prod);
		
		listProdView.setAdapter(adapter);
		
		listProdView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
			   Object o = listProdView.getItemAtPosition(position);
			   Toast.makeText(getBaseContext(),o.toString(),Toast.LENGTH_SHORT).show();
			   
	        }
	    });
    }
	
	public List<Produto> buscaProduto(){
    	
    	List<Produto> produtoList;
    	
    	NotaValorDB db = new NotaValorDB(this);
    	
    	produtoList = db.getTodasNotas();
    	
    	return produtoList;
    	
    }*/

}
