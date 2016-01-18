package com.econoom.auxiliar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.widget.Toast;

import com.econoom.Banco.NotaValorDB;
import com.econoom.R;
import com.econoom.entidade.NotaValor;

public class ListaNotasValor extends ArrayAdapter<NotaValor> {

	Context context;
	private int resource;
    List<NotaValor> data = null;
	private int tipoNota;
	private LinearLayout mes_cadastro;
	private TextView nm_mes;
	private String verificaMesAtual = "";
    private String verificaMesAnt = "";
    private final static String TAG = "ListaNotasValor";
    private int posicaoAnt;
    private int posicaoAtual;
    private NotaValor objectAnt;

	public ListaNotasValor(Context context, int resource, List<NotaValor> object, int tipoNota) {
        super(context, resource, object);
		this.context = context;
		this.resource = resource;
		this.data = object;
		this.tipoNota = tipoNota;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resource, parent,false);
		}

		//LinearLayout root = (LinearLayout) convertView.findViewById(R.id.row);
		/*if((position % 2) == 0){
			root.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}	
		else{
			root.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}*/

        LinearLayout root = (LinearLayout) convertView.findViewById(R.id.row);

		NotaValor object = data.get(position);
        posicaoAtual = position;

        if(position != 0)
            posicaoAnt = position - 1;
        else
            posicaoAnt = 0;

		if(tipoNota == 0){
			mostraProdutos(object, convertView);
		}else if(tipoNota == 1){
			mostraContas(object, convertView);
		}else{
			mostraServicos(object, convertView);
		}

        Log.d(TAG, "getView getTpPagamento: " + object.getTpPagamento());

        if(object.getTpPagamento() == 0){

            root.setBackgroundColor(ContextCompat.getColor(context, R.color.notaValorComprado));

        }

		/*TextView tv_numero = (TextView) convertView.findViewById(R.id.tv_numero);
		tv_numero.setText(object.numero);
		TextView tv_tempo = (TextView) convertView.findViewById(R.id.tv_tempo);
		tv_tempo.setText(String.valueOf(object.cont));*/
		
		return convertView;
	}

	public void mostraProdutos(NotaValor object, View convertView){

        Log.d(TAG, "mostraProdutos: " + object.getNome());

        verificaMes(object, convertView);

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

        Log.d(TAG, "mostraContas: "+object.getNome());

        verificaMes(object, convertView);

		TextView nm_conta = (TextView) convertView.findViewById(R.id.nm_conta);
		TextView vl_conta = (TextView) convertView.findViewById(R.id.vl_conta);

		nm_conta.setText(object.getNome());
		vl_conta.setText(String.format("%.2f", object.getValor()));

	}

	public void mostraServicos(NotaValor object, View convertView){

        Log.d(TAG, "mostraServicos: "+object.getNome());

        verificaMes(object, convertView);

		TextView nm_servico = (TextView) convertView.findViewById(R.id.nm_conta);
		TextView vl_servico = (TextView) convertView.findViewById(R.id.vl_conta);

		nm_servico.setText(object.getNome());
		vl_servico.setText(String.format("%.2f", object.getValor()));

	}

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public long getDateMillisegundos(String ano, String mes, String dia, boolean dataFinal){

        Calendar calendar = Calendar.getInstance();

        calendar.set(Integer.valueOf(ano), Integer.valueOf(mes) - 1, Integer.valueOf(dia));

        if(dataFinal){

            calendar.set(Integer.valueOf(ano), Integer.valueOf(mes)-1, calendar.getActualMaximum(calendar.DAY_OF_MONTH));

        }

        return calendar.getTimeInMillis();

    }

    public void verificaMes(NotaValor object, View convertView){


        mes_cadastro = (LinearLayout) convertView.findViewById(R.id.mes_cadastro);
        nm_mes = (TextView) convertView.findViewById(R.id.nm_mes);

        objectAnt = data.get(posicaoAnt);

        verificaMesAtual = getDate(object.getDataCadastro(), "MMMM");
        verificaMesAnt = getDate(objectAnt.getDataCadastro(), "MMMM");

        if(posicaoAtual == 0 || !verificaMesAtual.equals(verificaMesAnt)){

            long dataInicial;
            long dataFinal;
            double[] somaDebitoCredito;

            dataInicial = getDateMillisegundos(getDate(object.getDataCadastro(), "yyy"), getDate(object.getDataCadastro(), "MM"), "01", false);

            dataFinal = getDateMillisegundos(getDate(object.getDataCadastro(), "yyy"), getDate(object.getDataCadastro(), "MM"), "01", true);

            mes_cadastro.setVisibility(View.VISIBLE);

            //Log.d(TAG, );

            Log.d(TAG, "verificaMes data Inicial: " +
                            getDate(dataInicial,"yyyyy.MMMMM.dd")
            );

            Log.d(TAG, "verificaMes data final: " +
               getDate(dataFinal,"yyyyy.MMMMM.dd")
            );

            NotaValorDB db = new NotaValorDB(context);

            somaDebitoCredito = db.gastoIntervData(dataInicial, dataFinal, tipoNota);

            Log.d(TAG, "verificaMes debito: " + somaDebitoCredito[0]);

            Log.d(TAG, "verificaMes credito: " + somaDebitoCredito[1]);

            nm_mes.setText(verificaMesAtual+" Gasto total: R$"+ String.format("%.2f", somaDebitoCredito[0]));

        }else
            mes_cadastro.setVisibility(View.GONE);

    }

}
