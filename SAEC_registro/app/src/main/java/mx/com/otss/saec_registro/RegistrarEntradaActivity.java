package mx.com.otss.saec_registro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.saec_registro.auxiliares.ListadoReporteActivity;
import mx.com.otss.saec_registro.request.ReporteRegistrarRequest;
import mx.com.otss.saec_registro.request.RegistrarEntradaRequest;
import mx.com.otss.saec_registro.auxiliares.IntentIntegrator;
import mx.com.otss.saec_registro.auxiliares.IntentResult;

import static mx.com.otss.saec_registro.auxiliares.Red.verificaConexion;

public class RegistrarEntradaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button scanBoton;
    private TextView formatoTxt, matriculatxt,detalle;
    private String scanContent,user;
    private Menu menu;
    private MenuItem menuItem;
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
                                    JSONObject jsonResponse = new JSONObject(response);
                                    Log.i("Info", "" + jsonResponse.toString());
                                    List<String> list = new ArrayList<String>();
                                    JSONArray array = jsonResponse.getJSONArray("registro_diario");
                                    for(int i = 0 ; i < array.length() ; i++){
                                        list.add(array.getJSONObject(i).getString("id"));
                                        list.add(array.getJSONObject(i).getString("nombre"));
                                        list.add(array.getJSONObject(i).getString("dia"));
                                        list.add(array.getJSONObject(i).getString("hora_entrada"));
                                        list.add(array.getJSONObject(i).getString("hora_salida"));

                                    }
                                    Log.i("llego", "Llego"+ list.toString());
                                    Intent intentPrincipal = new Intent(getApplicationContext(), ListadoReporteActivity.class);
                                    intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) list);
                                    finish();
                                    startActivity(intentPrincipal);


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(RegistrarEntradaActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                                }
                            }
                        };

                        ReporteRegistrarRequest reporteRegistrarRequest = new ReporteRegistrarRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(RegistrarEntradaActivity.this);
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
        setContentView(R.layout.activity_registrar_entrada);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        scanBoton = (Button)findViewById(R.id.btn_registrar_entrada_scaneo);
        matriculatxt = (TextView)findViewById(R.id.registrar_entrada_matricula);
        detalle = (TextView)findViewById(R.id.registrar_entrada_detalles);
        scanBoton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btn_registrar_entrada_scaneo){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (!verificaConexion(getApplication())) {
            Toast.makeText(getBaseContext(),
                    "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                    .show();
        }else {

            if (scanningResult != null) {
                scanContent = scanningResult.getContents();
                detalle.setText(getText(R.string.detalles));
                matriculatxt.setText("Matricula: " + scanContent);
                user = scanContent;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            Log.i("Info", "" + jsonResponse.toString());
                            boolean success = jsonResponse.getBoolean("success");
                            if (success == false) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistrarEntradaActivity.this);
                                builder.setMessage("No existe el alumno")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            } else if (success == true) {
                                Toast.makeText(getApplication(), "registro correcto", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplication(), "Error " + e, Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                RegistrarEntradaRequest registrarEntradaRequest = new RegistrarEntradaRequest(user, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegistrarEntradaActivity.this);
                queue.add(registrarEntradaRequest);

            } else {
                Toast.makeText(getApplication(), "No se ha recibido datos del scaneo!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

