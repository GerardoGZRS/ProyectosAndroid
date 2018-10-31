package mx.com.otss.c3.acceso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mx.com.otss.c3.R;

public class PortadaaActivity extends AppCompatActivity {
    private Button btnacceder,btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portadaa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_portada);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flotante_portada);
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

        btnacceder=(Button)findViewById(R.id.boton_acceder);
        btnacceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt=new Intent(getApplication(),LoginnActivity.class);
                startActivity(intentt);
            }
        });

        btnsalir=(Button)findViewById(R.id.boton_inicio_salir);
        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
