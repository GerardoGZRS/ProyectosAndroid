package mx.com.otss.administrador.activities.proveedores;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import mx.com.otss.administrador.R;
import mx.com.otss.administrador.activities.MenuPrinicipalActivity;

public class MenuProveedoresActivity extends AppCompatActivity {
    private Button buttonInsertar, buttonConsultar, buttonEliminar;

    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_perfiles:
                    Intent intentM = new Intent(getApplicationContext(), MenuPrinicipalActivity.class);
                    startActivity(intentM);
                    return true;
                case R.id.menu_inferior_produccion:

                    return true;
                case R.id.menu_inferior_finanzas:
                    return true;
                case R.id.menu_inferior_reportes:
                    return true;
                case R.id.menu_inferior_ayuda:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_proveedores);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        buttonInsertar = (Button)findViewById(R.id.btnIngresarProveedor);
        buttonConsultar = (Button)findViewById(R.id.btnConsultarProveedor);
        buttonEliminar = (Button)findViewById(R.id.btnEliminarProveedor);

        buttonInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentInsertar = new Intent(getApplicationContext(), InsertarProveedoresActivity.class);
                startActivity(intentInsertar);
            }
        });

        buttonConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentConsultar = new Intent(getApplicationContext(), ConsultarProveedoresActivity.class);
                startActivity(intentConsultar);
            }
        });

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEliminar = new Intent(getApplicationContext(), EliminarProveedoresActivity.class);
                startActivity(intentEliminar);
            }
        });
    }
}
