package com.econoom.servicos;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.econoom.R;
import com.econoom.auxiliar.ListGprMatAdapter;
import com.econoom.entidade.GrupoMat;

import java.util.List;

public class ListaGprMatService extends AsyncTask<Void, Void, List<GrupoMat>> {

    private static final String TAG = "ListaGprMatService";

    private Context contexto;
    private ListView listGprMatView;

    public ListaGprMatService(Context contexto){

        this.contexto = contexto;

    }


    @Override
    protected List<GrupoMat> doInBackground(Void... params) {

        List<GrupoMat> grupoMatList;
        GrupoMat gprMat = new GrupoMat();

        grupoMatList = gprMat.listaTodosGprMat(contexto);

        return grupoMatList;
    }

    @Override
    protected void onPostExecute(List<GrupoMat> result) {

        ListGprMatAdapter adapter;

        adapter = new ListGprMatAdapter(contexto, R.layout.list_grp_mat_adapter, result);
        listGprMatView = (ListView) ((Activity) contexto).findViewById(R.id.list_gpr_mat);

        if(result != null){

            listGprMatView.setAdapter(adapter);

        }



    }
}
