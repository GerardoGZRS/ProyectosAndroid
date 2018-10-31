package mx.com.otss.c3.acceso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import mx.com.otss.c3.R;
import mx.com.otss.c3.bd.LoadDataBase;
import mx.com.otss.c3.contenidos.menu_inferior.ReglamentosCompletosActivity;

public class PortadaBienvenidaActivity extends AppCompatActivity {
    private TextView mensaje;
    private String usuario;
    private Button btnAcceder;
    private LoadDataBase db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada_bienvenida);
        //ESTE METODO COPIA LA BASE DE DATOS PRECARGADA A LA APLICACION
        db2 = new LoadDataBase(this);
        try {
            db2.createDataBase();
            db2.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mensaje=(TextView)findViewById(R.id.message);
        mensaje.setText(Html.fromHtml(getString(R.string.contentActivity)));
        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");
        btnAcceder = (Button)findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReglamento = new Intent(getApplicationContext(), ReglamentosCompletosActivity.class);
                intentReglamento.putExtra("usuario", usuario);
                finish();
                startActivity(intentReglamento);
            }
        });
    }
}
