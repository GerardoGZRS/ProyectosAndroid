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
import mx.com.otss.c3.acceso.request.RegisterRequest;

import static mx.com.otss.c3.acceso.Red.verificaConexion;

public class RegistrooActivity extends AppCompatActivity {
    private EditText nombre,paterno,materno,usuario,contraseña,correo,telefono;
    private Button btnregistrar;
    private String nombres,appat,apmat,nick,clavex,email,cel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registroo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_registro);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.flotante_registro);
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

        nombre=(EditText)findViewById(R.id.linea_nombres);
        paterno=(EditText)findViewById(R.id.linea_paterno);
        materno=(EditText)findViewById(R.id.linea_materno);
        usuario=(EditText)findViewById(R.id.linea_usuario);
        contraseña=(EditText)findViewById(R.id.linea_contraseña);
        correo=(EditText)findViewById(R.id.linea_correo);
        telefono=(EditText)findViewById(R.id.linea_telefono);

        btnregistrar=(Button)findViewById(R.id.registrarusu);
        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificaConexion(getApplication())) {
                    Toast.makeText(getBaseContext(),
                            "Comprueba tu conexión a Internet.... ", Toast.LENGTH_SHORT)
                            .show();
                }else {
                    nombres = nombre.getText().toString();
                    appat = paterno.getText().toString();
                    apmat = materno.getText().toString();
                    nick = usuario.getText().toString();
                    clavex = contraseña.getText().toString();
                    email = correo.getText().toString();
                    cel = telefono.getText().toString();

                    // Response received from the server
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    String mensaje = jsonResponse.getString("mensaje");
                                    Toast.makeText(getApplication(), mensaje, Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getApplication(), ConfirmacionnActivity.class);
                                    finish();
                                    startActivity(intent);
                                } else {
                                    String mensaje = jsonResponse.getString("mensaje");
                                    Toast.makeText(getApplication(), mensaje, Toast.LENGTH_LONG).show();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistrooActivity.this);
                                    builder.setMessage("error en el registro, prueba con otro usuario")
                                            .setNegativeButton("Retry", null)
                                            .create().show();
                                }
                            } catch (JSONException e) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistrooActivity.this);
                                builder.setMessage("error en el registro, prueba con otro usuario")
                                        .setNegativeButton("Retry", null)
                                        .create().show();
                            }
                        }
                    };


                    try {
                        if (nombres.length() == 0 || appat.length() == 0 || apmat.length() == 0 || nick.length() == 0 || clavex.length() == 0 || email.length() == 0 || cel.length() == 0) {
                            Toast.makeText(getApplicationContext(), "campos vacios!!", Toast.LENGTH_SHORT).show();
                        } else {
                            RegisterRequest registerRequest = new RegisterRequest(nombres, appat, apmat, nick, clavex, email, cel, responseListener);
                            RequestQueue queve = Volley.newRequestQueue(RegistrooActivity.this);
                            queve.add(registerRequest);
                        }
                    } catch (Exception e) {
                    }
                }
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
