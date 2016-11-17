package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;


public class PrestamoAdapter extends ArrayAdapter<ParseObject> {
    private final Activity context;
    private ParseObject[] data;
    int layoutResourceId;
    private final Integer imageId;


    public PrestamoAdapter(Activity context, int layoutResourceId, ParseObject[] data, Integer imageId) {
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
            holder.nombrede= (TextView) row.findViewById(R.id.nombreAmigoTxtp);
            holder.cantidad=(TextView) row.findViewById(R.id.cantidadp);
            holder.tiempo=(TextView) row.findViewById(R.id.tiempod);
            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }
        holder.nombrede.setText(data[position].getString("lender"));
        holder.cantidad.setText(data[position].getString("deuda"));
        ImageView imageView=(ImageView) row.findViewById(R.id.imgp);
        imageView.setImageResource(imageId);
        return row;
    }
    static class Holder {
        TextView nombrede;
        TextView cantidad;
        TextView tiempo;



    }
}