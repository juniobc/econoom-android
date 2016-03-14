package com.econoom;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by sebastiao on 14/03/2016.
 */
public class GprMatFragment extends android.support.v4.app.Fragment {

    private View view;
    private Button imgGpr;
    private ImageView imgFoto;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 12;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            /*ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);*/
            view = inflater.inflate(R.layout.gpr_mat_fragment, container, false);

        }

        imgGpr = (Button) view.findViewById(R.id.img_gpr);
        imgFoto = (ImageView) view.findViewById(R.id.img_foto);

        imgGpr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(i, 12);
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

}
