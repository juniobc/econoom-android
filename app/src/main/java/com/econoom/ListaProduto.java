package com.econoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.econoom.Banco.NotaValorDB;
import com.econoom.auxiliar.ListaNotasValor;
import com.econoom.entidade.NotaValor;
import com.econoom.entidade.Produto;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import java.util.List;

public class ListaProduto extends Fragment {

    private ListView listProdView;
    private final static String TAG = "ListaProduto";
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lista_produto, container, false);

        ListaNotasValor adapter = new ListaNotasValor(getActivity(),R.layout.list_lista_produto,buscaProduto(),0);

        listProdView = (ListView) view.findViewById(R.id.list_prod);

        listProdView.setAdapter(adapter);

        listProdView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Produto produto;

                produto = (Produto) parent.getItemAtPosition(position);

                if (!produto.getDescNotaValor().equals("")) {
                    Toast.makeText(getActivity(), produto.getDescNotaValor(), Toast.LENGTH_LONG).show();
                }

            }
        });

        listProdView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                // TODO Auto-generated method stub

                Intent i = new Intent(getActivity(), AlteraCadastro.class);

                Produto produto;

                produto = (Produto) arg0.getItemAtPosition(pos);

                i.putExtra(AlteraCadastro.OBJETO_PRODUTO, produto);
                startActivityForResult(i, 0);

                return true;
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
