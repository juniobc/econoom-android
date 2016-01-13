package com.econoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.econoom.Banco.NotaValorDB;
import com.econoom.auxiliar.ListaNotasValor;
import com.econoom.entidade.NotaValor;
import com.econoom.entidade.Produto;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import java.util.List;

public class ListaProduto extends Fragment {

    private ListView listProdView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lista_produto, container, false);

        ListaNotasValor adapter = new ListaNotasValor(getActivity(),R.layout.list_lista_produto,buscaProduto());

        listProdView = (ListView) view.findViewById(R.id.list_prod);

        listProdView.setAdapter(adapter);

        listProdView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listProdView.getItemAtPosition(position);
                Toast.makeText(getActivity(), o.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        return view;

    }

    public List<NotaValor> buscaProduto(){

        List<Produto> produtoList;

        NotaValorDB db = new NotaValorDB(getActivity());

        produtoList = db.getTodosProdutos();

        return (List<NotaValor>)(List<?>)produtoList;

    }

}
