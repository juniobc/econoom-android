package com.econoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import com.econoom.Banco.NotaValorDB;
import com.econoom.auxiliar.ListaNotasValor;
import com.econoom.entidade.Conta;
import com.econoom.entidade.NotaValor;

import java.util.List;

public class ListaConta extends Fragment {

    private final static String TAG = "ListaConta";
    private ListView listProdView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_produto, container, false);

        ListaNotasValor adapter = new ListaNotasValor(getActivity(),R.layout.list_lista_conta,buscaConta(),1);

        listProdView = (ListView) view.findViewById(R.id.list_prod);

        listProdView.setAdapter(adapter);

        listProdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listProdView.getItemAtPosition(position);
                Toast.makeText(getActivity(), o.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        return view;
    }

    public List<NotaValor> buscaConta() {

        List<Conta> contaList;

        NotaValorDB db = new NotaValorDB(getActivity());

        contaList = db.getTodasContas();

        return (List<NotaValor>) (List<?>) contaList;

    }

}
