package mx.com.otss.c3.acceso;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import mx.com.otss.c3.acceso.request.LoginRequest;

import static mx.com.otss.c3.acceso.Red.verificaConexion;

public class LoginnActivity extends AppCompatActivity {
    private EditText usuariox, passx;
    private Button btnregistrarse, btnloguearse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginn);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flotante_login);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String[] TO = {"hc@otss.com.mx"}; //aquí pon tu correo
                    String[] CC = {"asanchez@otss.com.mx"};
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setData(Uri.parse("mailto:"));
                    emailIntent.setType("text/plain");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                    emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Apoyo con lector de leyes");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                        finish();
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplication(),"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }
        });

        usuariox = (EditText) findViewById(R.id.login_usuario);
        passx = (EditText) findViewById(R.id.login_contraseña);

        btnloguearse =(Button)findViewById(R.id.btn_inicio_logueo);
        btnloguearse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    final String usuarioxx = usuariox.getText().toString();
                    final String pass = passx.getText().toString();

                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    String usuario = jsonResponse.getString("usuario");

                                    Intent intent = new Intent(getApplication(), PortadaBienvenidaActivity.class);
                                    intent.putExtra("usuario", usuario);
                                    startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginnActivity.this);
                                    builder.setMessage("Datos incorrectos, o sesion iniciada desde otro dispositivo")
                                            .setNegativeButton("Intentar nuevamente", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginnActivity.this);
                                builder.setMessage("Datos incorrectos, o sesion iniciada desde otro dispositivo")
                                        .setNegativeButton("Intentar nuevamente", null)
                                        .create()
                                        .show();
                            }
                        }
                    };

                    LoginRequest loginRequest = new LoginRequest(usuarioxx, pass, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(LoginnActivity.this);
                    queue.add(loginRequest);
                }
            }
        });

        btnregistrarse=(Button)findViewById(R.id.btn_inicio_registrar);
        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt=new Intent(getApplication(),RegistrooActivity.class);
                finish();
                startActivity(intentt);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_acceso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_acce_regresar_portada) {
            Intent intentt=new Intent(getApplication(),PortadaaActivity.class);
            finish();
            startActivity(intentt);
        }
        return super.onOptionsItemSelected(item);
    }
}
