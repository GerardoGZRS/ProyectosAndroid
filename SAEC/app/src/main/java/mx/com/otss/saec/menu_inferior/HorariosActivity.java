package mx.com.otss.saec.menu_inferior;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mx.com.otss.saec.R;
import mx.com.otss.saec.acceso.PortadaActivity;
import mx.com.otss.saec.adapdator.ListadoAdeudosAdapter;
import mx.com.otss.saec.adapdator.RecyclerTouchListener;
import mx.com.otss.saec.menu_inferior.horarios.RegistroAlumnosActivity;
import mx.com.otss.saec.menu_inferior.horarios.RegistroGeneralActivity;
import mx.com.otss.saec.request.CargarSpinnerRequest;
import mx.com.otss.saec.request.ListadoAdeudosRequest;
import mx.com.otss.saec.request.RegistroDiarioRequest;
import mx.com.otss.saec.usuarios.Tabla_Alumnos;

import static mx.com.otss.saec.acceso.Red.verificaConexion;

public class HorariosActivity extends AppCompatActivity {
    private Button btnListadoGeneral,btnListadoHAlumnos;
    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inferior_inicio:
                    Intent intent2=new Intent(getApplication(),PrincipalActivity.class);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inferior_listados:
                    Intent intent1=new Intent(getApplication(),ListadosActivity.class);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inferior_adeudos:
                    if (!verificaConexion(getApplication())) {
                        Toast.makeText(getBaseContext(),
                                "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                                .show();
                    }else{
                        String user="";
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = null;

                                    Log.i("recibo:", ""+ jsonArray.toString());
                                    ArrayList<String> listdata = new ArrayList<String>();

                                    if (jsonArray != null) {
                                        for (int i=0;i<jsonArray.length();i++){
                                            listdata.add(jsonArray.getString(i));
                                        }
                                    }

                                    Intent intentPrincipal=new Intent(getApplicationContext(), AdeudosActivity.class);
                                    intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                    finish();
                                    startActivity(intentPrincipal);

                                    Log.i("Data receive: " , "" + listdata.toString());

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        ListadoAdeudosRequest adeudosRequest = new ListadoAdeudosRequest(user, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(HorariosActivity.this);
                        queue.add(adeudosRequest);
                    }
                    return true;
                case R.id.menu_inferior_registro:
                    Intent intent4=new Intent(getApplication(),HorariosActivity.class);
                    finish();
                    startActivity(intent4);
                    return true;
                case R.id.menu_inferior_salir:
                    Intent intent5=new Intent(getApplication(),PortadaActivity.class);
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
        setContentView(R.layout.activity_horarios);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        menu = navigation.getMenu();
        menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        btnListadoGeneral = (Button)findViewById(R.id.btnActivityHorarioG);
        btnListadoGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else{
                    String user="";
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = null;
                                ArrayList<String> listdata = new ArrayList<String>();

                                if (jsonArray != null) {
                                    for (int i=0;i<jsonArray.length();i++){
                                        listdata.add(jsonArray.getString(i));
                                    }
                                }

                                Intent intentPrincipal=new Intent(getApplicationContext(), RegistroGeneralActivity.class);
                                intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                finish();
                                startActivity(intentPrincipal);

                                Log.i("Data receive: " , "" + listdata.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(HorariosActivity.this, "No hay registros", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };

                    RegistroDiarioRequest loginRequest = new RegistroDiarioRequest(user, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(HorariosActivity.this);
                    queue.add(loginRequest);
                }
            }
        });

        btnListadoHAlumnos = (Button)findViewById(R.id.btnActivityHorarioAlumno);
        btnListadoHAlumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else{
                    String user="";
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = null;

                                Log.i("recibo:", ""+ jsonArray.toString());
                                ArrayList<String> listdata = new ArrayList<String>();

                                if (jsonArray != null) {
                                    for (int i=0;i<jsonArray.length();i++){
                                        listdata.add(jsonArray.getString(i));
                                    }
                                }
                                Intent intentPrincipal=new Intent(getApplicationContext(), RegistroAlumnosActivity.class);
                                intentPrincipal.putStringArrayListExtra("data", (ArrayList<String>) listdata);
                                finish();
                                startActivity(intentPrincipal);

                                Log.i("Data receive: " , "" + listdata.toString());

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    CargarSpinnerRequest loginRequest = new CargarSpinnerRequest(user, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(HorariosActivity.this);
                    queue.add(loginRequest);
                }
            }
        });

    }
}
