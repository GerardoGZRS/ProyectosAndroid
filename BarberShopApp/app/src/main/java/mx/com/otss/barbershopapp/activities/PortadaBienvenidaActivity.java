package mx.com.otss.barbershopapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;

public class PortadaBienvenidaActivity extends AppCompatActivity {
    private Button btnIngresar;
    ArrayList<String> nameFranquicia =new ArrayList<String>();
    ArrayList<String> franquicia = new ArrayList<String>();

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada_bienvenida);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                    Intent intentPrincipal = new Intent(getApplicationContext(), PrincipalEmpleadosActivity.class);
                    finish();
                    startActivity(intentPrincipal);
                }
        });
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFranquicia() {
        return franquicia;
    }

    /**
     *
     * @param franquicia
     */
    public void setFranquicia(ArrayList<String> franquicia) {
        this.franquicia = franquicia;
    }
}
