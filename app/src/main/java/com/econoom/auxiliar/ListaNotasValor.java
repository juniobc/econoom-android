package com.econoom.auxiliar;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.app.Activity;

import com.econoom.R;
import com.econoom.entidade.NotaValor;

public class ListaNotasValor extends ArrayAdapter<NotaValor> {
	Context context;
	private int resource;
    List<NotaValor> data = null;
	private int tipoNota;

	public ListaNotasValor(Context context, int resource, List<NotaValor> object, int tipoNota) {
		super(context, resource, object);
		this.context = context;
		this.resource = resource;
		this.data = object;
		this.tipoNota = tipoNota;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

		//if(convertView == null){

			convertView = inflater.inflate(resource, parent,false);
		//}

		//LinearLayout root = (LinearLayout) convertView.findViewById(R.id.row);
		/*if((position % 2) == 0){
			root.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}	
		else{
			root.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}*/

		NotaValor object = data.get(position);

		if(tipoNota == 0){
			mostraProdutos(object, convertView);
		}else if(tipoNota == 1){
			mostraContas(object, convertView);
		}else{
			mostraServicos(object, convertView);
		}
		


		/*TextView tv_numero = (TextView) convertView.findViewById(R.id.tv_numero);
		tv_numero.setText(object.numero);
		TextView tv_tempo = (TextView) convertView.findViewById(R.id.tv_tempo);
		tv_tempo.setText(String.valueOf(object.cont));*/
		
		return convertView;
	}

	public void mostraProdutos(NotaValor object, View convertView){

		TextView nm_produto = (TextView) convertView.findViewById(R.id.nm_produto);
		TextView qt_produto = (TextView) convertView.findViewById(R.id.qt_produto);
		TextView vl_produto = (TextView) convertView.findViewById(R.id.vl_produto);
		String qtUnidadeMedida;

		qtUnidadeMedida = String.format("%.2f", object.getQtUnMedida());

		if(qtUnidadeMedida.split(",")[1].equals("00")){
			qtUnidadeMedida = qtUnidadeMedida.split(",")[0];
		}

		nm_produto.setText(object.getNome() + " - " +
				qtUnidadeMedida + " " + object.getTpUnidadeMedida());
		qt_produto.setText(String.format("%d", object.getQuantidade()));
		vl_produto.setText(String.format("%.2f", object.getValor()));

	}

	public void mostraContas(NotaValor object, View convertView){

		TextView nm_conta = (TextView) convertView.findViewById(R.id.nm_conta);
		TextView vl_conta = (TextView) convertView.findViewById(R.id.vl_conta);

		nm_conta.setText(object.getNome());
		vl_conta.setText(String.format("%.2f", object.getValor()));

	}

	public void mostraServicos(NotaValor object, View convertView){

		TextView nm_servico = (TextView) convertView.findViewById(R.id.nm_conta);
		TextView vl_servico = (TextView) convertView.findViewById(R.id.vl_conta);

		nm_servico.setText(object.getNome());
		vl_servico.setText(String.format("%.2f", object.getValor()));

	}

}
