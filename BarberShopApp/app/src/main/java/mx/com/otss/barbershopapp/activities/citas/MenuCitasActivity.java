package mx.com.otss.barbershopapp.activities.citas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.activities.comisiones.MenuComisionesActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ListadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalEmpleadosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalFranquiciasActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.PrincipalServiciosActivity;
import mx.com.otss.barbershopapp.activities.menu_inferior.ReportesActivity;
import mx.com.otss.barbershopapp.request.citas.ConsultaCitasRequest;
import mx.com.otss.barbershopapp.utils.BarberShop;
import mx.com.otss.barbershopapp.utils.Comunicador;

import static mx.com.otss.barbershopapp.utils.Red.verificaConexion;

public class MenuCitasActivity extends AppCompatActivity {
    private String nombreFranquicia;
    private Button btnCitaConsulta;
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
                    finish();
                    startActivity(intentPrincipalEmpleados);
                    return true;

                case R.id.menu_inferior_servicios:
                    Intent intentPrincipalServicios=new Intent(getApplication(),PrincipalServiciosActivity.class);
                    finish();
                    startActivity(intentPrincipalServicios);
                    return true;

                case R.id.menu_inferior_franquicias:
                    Intent intentPrincipalFranquicias = new Intent(getApplicationContext(), PrincipalFranquiciasActivity.class);
                    finish();
                    startActivity(intentPrincipalFranquicias);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intentPrincipalClientes = new Intent(getApplicationContext(), ListadosActivity.class);
                    finish();
                    startActivity(intentPrincipalClientes);
                    return true;
                case R.id.menu_inferior_reportes:
                    Intent intentReportes = new Intent(getApplicationContext(), ReportesActivity.class);
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
        setContentView(R.layout.activity_menu_citas);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        btnCitaConsulta = (Button)findViewById(R.id.btnCitasConsultas);
        btnCitaConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comunicador.limpiar();
                if (!verificaConexion(getApplication())) {
                    NotificationCompat.Builder mBuilder;
                    NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                    int icono = R.drawable.wifi;
                    Intent i= null;
                    try {
                        i = new Intent(getApplicationContext(), Class.forName(Settings.ACTION_WIRELESS_SETTINGS));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, i, 0);
                    mBuilder =new NotificationCompat.Builder(getApplicationContext())
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(icono)
                            .setContentTitle("Sin acceso a la red")
                            .setContentText("Revisar su conexión a Internet")
                            .setVibrate(new long[] {100, 250, 100, 500})
                            .setAutoCancel(true);
                    mNotifyMgr.notify(1, mBuilder.build());
                }else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        /**
                         *
                         * @param response
                         */
                        @Override
                        public void onResponse(String response) {
                            try{

                                JSONArray jsonResponse = new JSONArray(response);
                                Log.i("Info", "" + jsonResponse.length());
                                ArrayList<String> list = new ArrayList<String>();
                                String [] q = new String[jsonResponse.length()];
                                for(int x = 0; x<q.length; x ++){
                                    String v =   q[x] = jsonResponse.optString(x);
                                    Log.i("info", "Mi lista"+v );
                                }

                                for(String s: q){
                                    JSONObject jsonObject = new JSONObject(s);
                                    for(int z=0; z<jsonObject.length();z++) {
                                        JSONArray array = jsonObject.getJSONArray("citas");
                                        String cadena = null;
                                        Comunicador obj = new Comunicador();
                                        BarberShop o = new BarberShop();
                                        for (int i = 0; i < array.length(); i++) {

                                        o.setNombreCliente(array.getJSONObject(i).getString("nombreCliente"));
                                        o.setNombreEmpleado(array.getJSONObject(i).getString("nombreEmpleado"));
                                        o.setNombreServicio(array.getJSONObject(i).getString("nombreServicio"));
                                        o.setNombreFranquisia(array.getJSONObject(i).getString("nombreFranquicia"));
                                        o.setFechaInicio(array.getJSONObject(i).getString("fecha"));
                                        obj.setOBJ(o);

                                        }
                                    }
                                }

                                Intent intentCosultaCitas = new Intent(getApplicationContext(), ConsultaCitasActivity.class);
                                finish();
                                startActivity(intentCosultaCitas);

                            } catch (JSONException e) {
                                Log.e("Error", "Error" + e.getMessage());
                                e.printStackTrace();
                                Toast.makeText(MenuCitasActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    ConsultaCitasRequest consultaCitasRequest = new ConsultaCitasRequest(responseListener);
                    RequestQueue queue = Volley.newRequestQueue(MenuCitasActivity.this);
                    queue.add(consultaCitasRequest);

                }
            }
        });
    }
    //menu

    /**
     *
     * @param menu
     * @return
     */
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
