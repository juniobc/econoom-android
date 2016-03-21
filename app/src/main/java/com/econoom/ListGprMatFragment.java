package com.econoom;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.econoom.entidade.GrupoMat;
import com.econoom.servicos.ListaGprMatService;

public class ListGprMatFragment extends Fragment {

    private View view;
    private ListView listGprMat;
    private static GrupoMat gprMat;
    private boolean mCalled = false;
    private OnGprMatSelectedListener mListener;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            /*ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);*/
            view = inflater.inflate(R.layout.list_gpr_fragment, container, false);

        }

        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Lista Grupo Material");
        }

        new ListaGprMatService(getContext()).execute();

        listGprMat = (ListView) view.findViewById(R.id.list_gpr_mat);

        listGprMat.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                gprMat = (GrupoMat) parent.getItemAtPosition(position);

                builder.setMessage("Selecione uma opção!")
                        .setPositiveButton(R.string.btn_altera, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mListener.onGprMatSelected(gprMat);

                            }
                        }).setNegativeButton(R.string.btn_deleta, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        gprMat.deletaGrpMat(getContext());

                        Toast.makeText(getContext(), "Deletado com sucesso!", Toast.LENGTH_SHORT).show();

                        new ListaGprMatService(getContext()).execute();

                    }
                }).show();

            }
        });

        return view;

    }

    public interface OnGprMatSelectedListener {
        public void onGprMatSelected(GrupoMat gprMat);
    }

    @Override
    public void onAttach (Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            mListener = (OnGprMatSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " tem que implementar onGprMatSelectedListener()");
        }
    }

}
