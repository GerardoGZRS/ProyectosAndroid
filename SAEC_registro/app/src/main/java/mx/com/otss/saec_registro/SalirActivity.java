package mx.com.otss.saec_registro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import mx.com.otss.saec_registro.auxiliares.ListadoReporteActivity;
import mx.com.otss.saec_registro.request.ReporteRegistrarRequest;

import static mx.com.otss.saec_registro.auxiliares.Red.verificaConexion;

public class SalirActivity extends AppCompatActivity {
    private Menu menu;
    private MenuItem menuItem;
    private Button btnSalir;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_inicio:
                    Intent intent1=new Intent(getApplication(),RegistrarActivity.class);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inferior_entrada:
                    Intent intent2=new Intent(getApplication(),RegistrarEntradaActivity.class);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inferior_salida:
                    Intent intent3=new Intent(getApplication(),RegistrarSalidaActivity.class);
                    finish();
                    startActivity(intent3);
                    return true;
                case R.id.menu_inferior_reporte:
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else {
                        final ArrayList<String> arrayList = new ArrayList<>();
                        String user = "";
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = null;
                                    Log.i("Recibo", "" + jsonArray);
                                    if (jsonArray == null) {
                                        Toast.makeText(SalirActivity.this, jsonArray.toString(), Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.i("recibo:", "" + jsonArray.toString());
                                        ArrayList<String> listdata = new ArrayList<String>();

                                        if (jsonArray != null) {
                                            for (int i = 0; i < jsonArray.length(); i++) {
                                                listdata.add(jsonArray.getString(i));
                                            }
                                        }
                                        Intent intentPrincipal = new Intent(getApplicationContext(), ListadoReporteActivity.class);
                                        intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                        finish();
                                        startActivity(intentPrincipal);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(SalirActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ReporteRegistrarRequest reporteRegistrarRequest = new ReporteRegistrarRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(SalirActivity.this);
                        queue.add(reporteRegistrarRequest);
                    }
                    return true;
                case R.id.menu_inferior_salir:
                    Intent intent5=new Intent(getApplication(),SalirActivity.class);
                    finish();
                    startActivity(intent5);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salir);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
