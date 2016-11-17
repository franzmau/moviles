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
        setContentView(R.layout.activity_addprestamos);
        Button button = (Button) findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Inicio de sesión
                TextView name_l=(TextView)findViewById(R.id.name_p);
                TextView quantity=(TextView)findViewById(R.id.quantity);
                TextView date=(TextView)findViewById(R.id.date);
                int  deuda=Integer.valueOf(quantity.getText().toString());
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date da = null;
                try {
                    da = formatter.parse(date.getText().toString());
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                ParseObject prestamo = new ParseObject("prestamos");
                prestamo.put("lender", name_l.getText().toString());
                prestamo.put("borrower", ParseUser.getCurrentUser().getUsername());
                prestamo.put("fechapago", date.toString());
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

            }
        });
    }
}
