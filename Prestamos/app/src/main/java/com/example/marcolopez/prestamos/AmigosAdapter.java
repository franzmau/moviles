package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;

/**
 * Created by Carlos on 15/11/16.
 */

public class AmigosAdapter extends ArrayAdapter<ParseObject> {
    private final Activity context;
    private ParseObject[] data;
    int layoutResourceId;
    private final Integer imageId;
    public AmigosAdapter(Activity context, int layoutResourceId, ParseObject[] data, Integer imageId) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.imageId = imageId;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;
        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new Holder();
            holder.nombre = (TextView) row.findViewById(R.id.nombreAmigoTxt);

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        holder.nombre.setText(data[position].getString("nombre"));
        //ImageView imageView=(ImageView) row.findViewById(R.id.img);
        //imageView.setImageResource(imageId);
        return row;
    }
    static class Holder {
        TextView nombre;
    }
}