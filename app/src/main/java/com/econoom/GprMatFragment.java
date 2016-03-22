package com.econoom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.econoom.entidade.GrupoMat;

import java.io.ByteArrayOutputStream;


public class GprMatFragment extends android.support.v4.app.Fragment {

    private static final String TAG = "GprMatFragment";

    private View view;
    private Button imgGpr;
    private ImageView imgFoto;
    private Button btnCad;
    private Button btnAlt;
    private EditText dsGprMat;
    private GrupoMat gprMat;
    private TextView cdGprMat;

    public static final String OBJETO_GPRMAT = "com.econoom.GprMatFragment.gprmat";

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 12;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            /*ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);*/
            view = inflater.inflate(R.layout.gpr_mat_fragment, container, false);

        }

        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Gerencia Grupo Material");
        }

        btnCad = (Button) view.findViewById(R.id.btn_cad);
        btnAlt = (Button) view.findViewById(R.id.btn_alt);
        imgGpr = (Button) view.findViewById(R.id.img_gpr);
        imgFoto = (ImageView) view.findViewById(R.id.img_foto);
        dsGprMat = (EditText) view.findViewById(R.id.ds_gpr_mat);
        cdGprMat = (TextView) view.findViewById(R.id.cd_gpr_mat);

        savedInstanceState = this.getArguments();

        if(savedInstanceState!=null){

            gprMat = savedInstanceState.getParcelable(OBJETO_GPRMAT);

            btnCad.setVisibility(View.GONE);
            btnAlt.setVisibility(View.VISIBLE);

            dsGprMat.setText(gprMat.getDsGrupo());

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = false;
            options.inTempStorage = new byte[1024 *32];

            Bitmap bm = BitmapFactory.decodeByteArray(gprMat.getImgGprMat(), 0, gprMat.getImgGprMat().length, options);
            imgFoto.setImageBitmap(bm);

            cdGprMat.setText(String.valueOf(gprMat.getCdGprMat()));

        }

        imgGpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, 12);
            }
        });

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgFoto.setDrawingCacheEnabled(true);

                imgFoto.buildDrawingCache();

                Bitmap bm = imgFoto.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                if(dsGprMat.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Informe uma descrição!", Toast.LENGTH_SHORT).show();

                }else{

                    GrupoMat gpr = new GrupoMat(dsGprMat.getText().toString(), byteArray);
                    gpr.cadastro(getContext());
                    limpaCampos();
                }
            }
        });

        btnAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imgFoto.setDrawingCacheEnabled(true);

                imgFoto.buildDrawingCache();

                Bitmap bm = imgFoto.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                if(dsGprMat.getText().toString().equals("")){

                    Toast.makeText(getContext(), "Informe uma descrição!", Toast.LENGTH_SHORT).show();

                }else{

                    GrupoMat gpr = new GrupoMat(Integer.parseInt(cdGprMat.getText().toString()),dsGprMat.getText().toString(), byteArray);

                    if(gpr.alteraGprMat(getContext())){
                        Fragment fragment = new ListGprMatFragment();
                        FragmentTransaction ft  = getFragmentManager().beginTransaction();
                        ft.replace(R.id.menu_fragment, fragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }

                }
            }
        });

        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == getActivity().RESULT_OK) {
                Uri photoUri = data.getData();
                if (photoUri != null){

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContext().getContentResolver().query(photoUri,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap bMap = BitmapFactory.decodeFile(filePath);
                    imgFoto.setImageBitmap(bMap);

                }


            } else if (resultCode == getActivity().RESULT_CANCELED) {
                // User cancelled the image capture
            } else {
                Toast.makeText(getActivity(), "Ocorreu um erro tente novamente!" +
                        data.getData(), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void limpaCampos(){

        imgFoto.setImageResource(R.drawable.sem_imagem);
        dsGprMat.setText("");
        cdGprMat.setText("");

    }

}
