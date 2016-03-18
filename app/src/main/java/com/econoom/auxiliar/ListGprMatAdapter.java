package com.econoom.auxiliar;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.econoom.R;
import com.econoom.entidade.GrupoMat;

import java.util.List;


public class ListGprMatAdapter extends ArrayAdapter<GrupoMat> {

    private static final String TAG ="ListGprMatAdapter";
    Context context;
    private int resource;
    private List<GrupoMat> data = null;

    public ListGprMatAdapter(Context context, int resource, List<GrupoMat> object) {
        super(context, resource, object);
        this.context = context;
        this.resource = resource;
        this.data = object;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent,false);
        }

        RelativeLayout root = (RelativeLayout) convertView.findViewById(R.id.row);

        GrupoMat object = (GrupoMat) data.get(position);

        FrameLayout img = (FrameLayout) convertView.findViewById(R.id.img_foto);
        TextView dsGprMat = (TextView) convertView.findViewById(R.id.ds_gpr_mat);
        TextView cdGprMat = (TextView) convertView.findViewById(R.id.cd_gpr_mat);

        dsGprMat.setText(object.getDsGrupo());

        cdGprMat.setText(String.valueOf(object.getCdGprMat()));

        Drawable background = img.getBackground();

        if (background instanceof ShapeDrawable) {
            // cast to 'ShapeDrawable'
            ShapeDrawable shapeDackground = (ShapeDrawable)background;

            shapeDackground.getPaint().setColor(ContextCompat.getColor(context, R.color.white));

        } else if (background instanceof GradientDrawable) {

            GradientDrawable gradientDrawable = (GradientDrawable)background;

            gradientDrawable.setColor(ContextCompat.getColor(context, R.color.white));
        }


        return convertView;

    }

}
