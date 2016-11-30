package com.example.marcolopez.prestamos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Objects;

public class PagarActivity extends AppCompatActivity {

    TextView name_l;
    TextView quantity;
    TextView pagado;
    Double debe;
    Double paga;
    String idstring;
    Double parcial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprestamo);

        Button button = (Button) findViewById(R.id.pagar);
        Intent i = getIntent();
        idstring=i.getStringExtra("stringID");

        Log.e("haoal",idstring);
        name_l=(TextView) findViewById(R.id.amigo_pago);
        pagado=(TextView) findViewById(R.id.pagad);
        quantity = (TextView) findViewById(R.id.pago_ca);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");

        query.getInBackground(i.getStringExtra("stringID"), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    name_l.setText((String) object.get("borrower"));
                    name_l.setFocusable(false);
                    debe=object.getDouble("deuda")-object.getDouble("parcial");
                    parcial=object.getDouble("parcial");
                    double pago=debe;
                    pagado.setText(String.valueOf(pago));
                    pagado.setFocusable(false);
                } else {
                    // something went wrong
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paga =Double.parseDouble(quantity.getText().toString());

                if(paga>debe){
                    Toast.makeText(PagarActivity.this, "No te deben tanto", Toast.LENGTH_LONG).show();
                }
                else {
                    new AlertDialog.Builder(PagarActivity.this)
                            .setTitle("Registrar pago")
                            .setMessage("Estas seguro que quieres registrar el pago de $" + paga + "?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");
                                    query.getInBackground(idstring, new GetCallback<ParseObject>() {
                                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                        public void done(ParseObject object, ParseException e) {
                                            if (e == null) {
                                                // Now let's update it with some new data. In this case, only cheatMode and score
                                                // will get sent to the Parse Cloud. playerName hasn't changed.
                                                object.put("parcial", paga+parcial);
                                                Log.e("pa", String.valueOf(paga));
                                                Log.e(String.valueOf(debe),"de");
                                                if (Objects.equals(paga, debe)) {
                                                    object.put("pagado", true);
                                                }
                                                object.saveInBackground();

                                            }
                                        }
                                    });
                                    Intent i = new Intent(PagarActivity.this, Profile.class);
                                    startActivity(i);

                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }

                    }
                });


            }
}
