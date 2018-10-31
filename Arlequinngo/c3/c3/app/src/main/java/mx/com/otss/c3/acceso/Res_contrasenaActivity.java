package mx.com.otss.c3.acceso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import mx.com.otss.c3.R;
import mx.com.otss.c3.acceso.request.PasswordRequest;
import mx.com.otss.c3.chat.ChatActivity;
import mx.com.otss.c3.contenidos.menu_inferior.EnviarPdfsActivity;
import mx.com.otss.c3.contenidos.SalirActivity;
import mx.com.otss.c3.contenidos.menu_inferior.DictarActivity;
import mx.com.otss.c3.contenidos.menu_inferior.UsuarioActivity;

import static mx.com.otss.c3.acceso.Red.verificaConexion;

public class Res_contrasenaActivity extends AppCompatActivity {
    private EditText pasw1, pasw2;
    private Button btnres_passw;
    private String usuario;
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_inf_regresar:
                    Intent intent2=new Intent(getApplication(),UsuarioActivity.class);
                    intent2.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent2);
                    return true;
                case R.id.menu_inf_usuario:
                    Intent intent1=new Intent(getApplication(),UsuarioActivity.class);
                    intent1.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent1);
                    return true;
                case R.id.menu_inf_dictar:
                    Intent intent3=new Intent(getApplication(),DictarActivity.class);
                    intent3.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent3);
                    return true;
                case R.id.menu_inf_pdf:
                    Intent intent4=new Intent(getApplication(),EnviarPdfsActivity.class);
                    intent4.putExtra("usuario", usuario);
                    finish();
                    startActivity(intent4);
                    return true;
                case R.id.menu_inf_salir:
                    Intent intent5=new Intent(getApplication(),SalirActivity.class);
                    intent5.putExtra("usuario", usuario);
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
        setContentView(R.layout.activity_res_contrasena);

        Intent intent_receptor = getIntent();
        usuario = intent_receptor.getStringExtra("usuario");

        pasw1 =(EditText)findViewById(R.id.res_linea_contraseña_nueva);
        pasw2 =(EditText)findViewById(R.id.res_linea_contraseña_validada);

        btnres_passw =(Button)findViewById(R.id.btn_res_contraseña);
        btnres_passw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    if (pasw1.length() == 0 || pasw2.length() == 0) {
                        Toast.makeText(getApplicationContext(), "campos vacios!!", Toast.LENGTH_SHORT).show();
                    } else if (pasw1.getText().toString().equals(pasw2.getText().toString())) {

                        final String passwnuevoxx = pasw2.getText().toString();

                        // Response received from the server
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if (success) {
                                        Intent intentt = new Intent(getApplication(), SalirActivity.class);
                                        intentt.putExtra("usuario", usuario);
                                        finish();
                                        startActivity(intentt);
                                        Toast.makeText(getApplication(), "Datos modificados, revisa tu correo", Toast.LENGTH_SHORT).show();
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplication());
                                        builder.setMessage("Algo falló")
                                                .setNegativeButton("Intentar nuevamente", null)
                                                .create()
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        };

                        PasswordRequest passwordRequest = new PasswordRequest(usuario, passwnuevoxx, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(Res_contrasenaActivity.this);
                        queue.add(passwordRequest);
                    } else {
                        Toast.makeText(getApplicationContext(), "campos no coinciden!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_superior_usuario) {
            Intent intentt=new Intent(getApplication(),UsuarioActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_chat) {
            Intent intentt=new Intent(getApplication(),ChatActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_contraseña) {
            Intent intentt=new Intent(getApplication(),Res_contrasenaActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        if (id == R.id.menu_superior_user) {
            Intent intentt=new Intent(getApplication(),Res_usuarioActivity.class);
            intentt.putExtra("usuario", usuario);
            finish();
            startActivity(intentt);
        }
        return super.onOptionsItemSelected(item);
    }
}
