package com.econoom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.econoom.Banco.NotaValorDB;
import com.econoom.auxiliar.ListaNotasValor;
import com.econoom.entidade.Conta;
import com.econoom.entidade.NotaValor;
import com.econoom.entidade.Produto;
import com.econoom.entidade.Servico;

import java.util.List;

public class ListaServico extends Fragment {

    private ListView listProdView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_produto, container, false);

        ListaNotasValor adapter = new ListaNotasValor(getActivity(),R.layout.list_lista_conta,buscaServico(),2);

        listProdView = (ListView) view.findViewById(R.id.list_prod);

        listProdView.setAdapter(adapter);

        listProdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Servico servico;

                servico = (Servico) parent.getItemAtPosition(position);

                if (servico.getDescNotaValor() != null) {
                    Toast.makeText(getActivity(), servico.getDescNotaValor(), Toast.LENGTH_LONG).show();
                }

            }
        });

        listProdView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                // TODO Auto-generated method stub

                Intent i = new Intent(getActivity(), CadastroProduto.class);

                Servico servico;

                servico = (Servico) arg0.getItemAtPosition(pos);

                i.putExtra(CadastroProduto.OBJETO_SERVICO, servico);
                startActivityForResult(i, 0);

                return true;
            }

        });

        return view;
    }

    public List<NotaValor> buscaServico(){

        List<Servico> servicoList;

        NotaValorDB db = new NotaValorDB(getActivity());

        servicoList = db.getTodosServicos();

        return (List<NotaValor>)(List<?>)servicoList;

    }

}
