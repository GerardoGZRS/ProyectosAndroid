package mx.com.otss.administrador.activities;

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
import mx.com.otss.administrador.activities.clientes.MenuClientesActivity;
import mx.com.otss.administrador.activities.empleados.MenuEmpleadosActivity;
import mx.com.otss.administrador.activities.maquileros.MenuMaquilerosActivity;
import mx.com.otss.administrador.activities.proveedores.MenuProveedoresActivity;

public class MenuPrinicipalActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4;
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
        setContentView(R.layout.activity_menu_prinicipal);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        btn1 = (Button)findViewById(R.id.btnEmpleado);
        btn2 = (Button)findViewById(R.id.btnMaquilero);
        btn3 = (Button)findViewById(R.id.btnProveedor);
        btn4 = (Button)findViewById(R.id.btnCliente);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEmpleados = new Intent(getApplicationContext(), MenuEmpleadosActivity.class);
                startActivity(intentEmpleados);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intentMaquilero = new Intent(getApplicationContext(), MenuMaquilerosActivity.class);
            startActivity(intentMaquilero);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intentProveedor = new Intent(getApplicationContext(), MenuProveedoresActivity.class);
            startActivity(intentProveedor);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intentClientes = new Intent(getApplicationContext(), MenuClientesActivity.class);
            startActivity(intentClientes);
            }
        });


    }
}
