package mx.com.otss.c3.acceso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mx.com.otss.c3.R;

public class ConfirmacionnActivity extends AppCompatActivity {
    private Button btnregreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacionn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_confirmacion);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flotante_confirmacion);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] TO = {"hc@otss.com.mx"}; //aquí pon tu correo
                    String[] CC = {"asanchez@otss.com.mx"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Apoyo con lector de leyes");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplication(),"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }
        });

        btnregreso=(Button)findViewById(R.id.btn_confirmacion_regreso);
        btnregreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intentt=new Intent(getApplication(),LoginnActivity.class);
                    finish();
                    startActivity(intentt);
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acceso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_acce_regresar_portada) {
            Intent intentt=new Intent(getApplication(),PortadaaActivity.class);
            finish();
            startActivity(intentt);
        }
        return super.onOptionsItemSelected(item);
    }
}
