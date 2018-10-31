package mx.com.otss.saec.acceso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import mx.com.otss.saec.R;
import mx.com.otss.saec.menu_inferior.PrincipalActivity;

public class PortadaActivity extends AppCompatActivity {
    private TextView btnIngresar,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        btnIngresar = (TextView)findViewById(R.id.txt_portada_acceder);
        btnSalir = (TextView)findViewById(R.id.txt_portada_salir);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                finish();
                startActivity(intent);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
