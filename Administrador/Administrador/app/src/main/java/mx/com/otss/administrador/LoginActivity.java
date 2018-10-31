package mx.com.otss.administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.com.otss.administrador.activities.MenuPrinicipalActivity;

public class LoginActivity extends AppCompatActivity {
    private Button btnIngresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnIngresar = (Button)findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentIngresar = new Intent(getApplicationContext(), MenuPrinicipalActivity.class);
                startActivity(intentIngresar);
            }
        });
    }
}
