package com.example.marcolopez.prestamos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.Console;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mauricio on 16/11/16.
 */

public class AddPrestamo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprestamo);
        Button button = (Button) findViewById(R.id.pagar);
        Intent i = getIntent();
        Log.e("TAG", i.getStringExtra("stringID"));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicio de sesión
                TextView name_l = (TextView) findViewById(R.id.amigo_pago);
                TextView quantity = (TextView) findViewById(R.id.pago_ca);


            /*

                ParseQuery<ParseObject> query = ParseQuery.getQuery("prestamos");

                // Retrieve the object by id
                query.getInBackground("", new GetCallback<ParseObject>() {
                    public void done(ParseObject gameScore, ParseException e) {
                        if (e == null) {
                            // Now let's update it with some new data. In this case, only cheatMode and score
                            // will get sent to the Parse Cloud. playerName hasn't changed.
                            gameScore.put("score", 1338);
                            gameScore.put("cheatMode", true);
                            gameScore.saveInBackground();
                        }
                    }
                });






                ParseObject prestamo = new ParseObject("prestamos");
                prestamo.put("lender", name_l.getText().toString());
                prestamo.put("borrower", ParseUser.getCurrentUser().getUsername());



                prestamo.put("deuda", deuda);
                prestamo.put("fechapago", da);
                prestamo.put("parcial", 0);
                prestamo.put("pagado", false);
                prestamo.saveInBackground(new SaveCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            Intent i = new Intent(AddPrestamo.this, Deudas.class);
                            startActivity(i);
                            finish();
                        } else {

                            Log.i("Registro", "" + e.getMessage());
                            Toast.makeText(AddPrestamo.this, "No se pudo registrar", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                    */
            }
        });
    }
}
