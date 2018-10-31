package mx.com.otss.otssaplicacion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    android.webkit.WebView myWebView = (android.webkit.WebView) findViewById(R.id.webViewIndex);
    try {
        android.webkit.WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new android.webkit.WebViewClient());
        myWebView.loadUrl("http://192.168.1.90/OTSS_MOVIL/equipo.html");
    }catch (Exception e){
        e.printStackTrace();
        Toast.makeText(this, "Error innesperado", Toast.LENGTH_SHORT).show();
    }
    }

}
