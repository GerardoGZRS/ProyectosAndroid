package mx.com.otss.barbershopapp.activities.menu_inferior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.citas.MenuCitasActivity;
import mx.com.otss.barbershopapp.activities.clientes.PrincipalClientesActivity;
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.cortesias.MenuCortesiasActivity;
import mx.com.otss.barbershopapp.activities.pagos.MenuPagosActivity;
import mx.com.otss.barbershopapp.activities.promociones.MenuPromocionesActivity;

public class ListadosActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        /**
         *
         * @param item
         * @return
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_empleados:
                    Intent intentPrincipalEmpleados=new Intent(getApplication(),PrincipalEmpleadosActivity.class);
                    intentPrincipalEmpleados.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalEmpleados);
                    return true;

                case R.id.menu_inferior_servicios:
                    Intent intentPrincipalServicios=new Intent(getApplication(),PrincipalServiciosActivity.class);
                    intentPrincipalServicios.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalServicios);
                    return true;

                case R.id.menu_inferior_franquicias:
                    Intent intentPrincipalFranquicias = new Intent(getApplicationContext(), PrincipalFranquiciasActivity.class);
                    intentPrincipalFranquicias.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalFranquicias);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intentPrincipalClientes = new Intent(getApplicationContext(), ListadosActivity.class);
                    intentPrincipalClientes.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentPrincipalClientes);
                    return true;
                case R.id.menu_inferior_reportes:
                    Intent intentReportes = new Intent(getApplicationContext(), ReportesActivity.class);
                    intentReportes.putExtra("nombreFranquicia", getNombreFranquicia());
                    finish();
                    startActivity(intentReportes);
                    return true;
            }
            return false;
        }
    };

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listados);
        Intent intent_receptor = getIntent();
        nombreFranquicia = intent_receptor.getStringExtra("nombreFranquicia");
        setNombreFranquicia(nombreFranquicia);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        btn1 = (Button)findViewById(R.id.btnClientes);
        btn2 = (Button)findViewById(R.id.btnCitas);
        btn3 = (Button)findViewById(R.id.btnComisiones);
        btn4 = (Button)findViewById(R.id.btnCortesia);
        btn5 = (Button)findViewById(R.id.btnPagos);
        btn6 = (Button)findViewById(R.id.btnPromociones);

        btn1.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), PrincipalClientesActivity.class);
                intent1.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), MenuCitasActivity.class);
                intent2.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), MenuComisionesActivity.class);
                intent3.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getApplicationContext(), MenuCortesiasActivity.class);
                intent4.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent4);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getApplicationContext(), MenuPagosActivity.class);
                intent5.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(getApplicationContext(), MenuPromocionesActivity.class);
                intent6.putExtra("nombreFranquicia", getNombreFranquicia());
                finish();
                startActivity(intent6);
            }
        });

    }

    /**
     *
     * @param menu
     * @return
     */
    //menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_superior_otros){
            Intent intentMenuComisiones = new Intent(getApplicationContext(), MenuComisionesActivity.class);
            startActivity(intentMenuComisiones);
        }
        if (id == R.id.menu_superior_salir) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * @return
     */
    public String getNombreFranquicia() {
        return nombreFranquicia;
    }

    /**
     *
     * @param nombreFranquicia
     */
    public void setNombreFranquicia(String nombreFranquicia) {
        this.nombreFranquicia = nombreFranquicia;
    }
}
