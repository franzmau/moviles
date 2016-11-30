package com.example.marcolopez.prestamos;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;



public class DeudasAdapter extends ArrayAdapter<ParseObject> {
    private final Activity context;
    private ParseObject[] data;
    int layoutResourceId;
    private final Integer imageId;
    public int i=0;



    public DeudasAdapter(Activity context, int layoutResourceId, ParseObject[] data, Integer imageId, int i) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
        this.imageId = imageId;
        this.i=i;
        for (int ih=0;ih<data.length;ih++){
            System.out.println(data[ih].getDouble("deuda")+" aqui estan los datos");
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder = null;
        if (row == null) {
                if(get_i()==0) {
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    row = inflater.inflate(layoutResourceId, parent, false);
                    holder = new Holder();
                    holder.nombrede = (TextView) row.findViewById(R.id.nombreAmigoTxtd);
                    holder.cantidad = (TextView) row.findViewById(R.id.cantidadd);
                    holder.tiempo = (TextView) row.findViewById(R.id.tiempod);
                    row.setTag(holder);
                }else{
                    LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                    row = inflater.inflate(layoutResourceId, parent, false);
                    holder = new Holder();
                    holder.nombrep = (TextView) row.findViewById(R.id.nombreAmigoTxtp);
                    holder.cantidap = (TextView) row.findViewById(R.id.cantidadp);
                    holder.tiempop = (TextView) row.findViewById(R.id.tiempop);
                    row.setTag(holder);
                }

        } else {
            holder = (Holder) row.getTag();
        }

           if(get_i()==0) {
               holder.nombrede.setText(data[position].getString("lender"));
               String a=String.valueOf(data[position].getInt("deuda")-data[position].getInt("parcial"));

               holder.cantidad.setText(a);
               ImageView imageView = (ImageView) row.findViewById(R.id.imgd);
               imageView.setImageResource(imageId);
           }else {
               holder.nombrep.setText(data[position].getString("borrower"));
               String a=String.valueOf(data[position].getInt("deuda")-data[position].getInt("parcial"));

               holder.cantidap.setText(a);
               ImageView imageView = (ImageView) row.findViewById(R.id.imgp);
               imageView.setImageResource(imageId);
           }
        return row;
    }


    public int get_i(){
        return this.i;
    }

    static class Holder {
        TextView nombrede;
        TextView cantidad;
        TextView tiempo;

        TextView nombrep;
        TextView cantidap;
        TextView tiempop;


    }
}