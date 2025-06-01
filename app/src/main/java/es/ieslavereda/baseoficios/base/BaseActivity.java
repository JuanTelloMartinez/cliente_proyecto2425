package es.ieslavereda.baseoficios.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import es.ieslavereda.baseoficios.API.Connector;
import es.ieslavereda.baseoficios.activities.model.Oficio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BaseActivity extends AppCompatActivity {

    protected Connector connector;
    protected ExecutorService executor = Executors.newSingleThreadExecutor();
    protected Handler handler = new Handler(Looper.getMainLooper());
    protected MyProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connector = Connector.getConector();
        progressBar = new MyProgressBar(this);
    }

    protected <T> void executeCall(CallInterface<T> callInterface){
        Log.w("principioExecuteCall", "principioExecuteCall: ");
        showProgress();
        Log.w("antesExecutor", "antesExecutor: ");
        executor.execute(() -> {
            Log.w("justoAntesdeltry", "justoAntesdeltry: ");
            try {
                Log.w("detrotry", "detrotry: ");
                Oficio.cargarOficiosDesdeDataSource();
                T data = callInterface.doInBackground();
                Log.w("justodespuésDoInBackroud", "justodespuésDoInBackroud: ");
                handler.post(() -> {
                    Log.w("detrohandlerpost", "detrohandlerpost: ");
                    hideProgress();
                    callInterface.doInUI(data);
                });
            } catch (Exception e){
                handler.post(()->{
                    Log.w("lanzaExcepción", "lanzaExcepción: ");
                    hideProgress();
                    callInterface.doInError(BaseActivity.this,e);
                });
            }
        });
        Log.w("finalExecuteCall", "finalExecuteCall: ");
    }

    public void showProgress(){
        progressBar.show();
    }

    public void hideProgress(){
        progressBar.hide();
    }


    // Sobreescribimos el metodo para asociar a la barra de progreso al ContraintLayout o RelativeLayout
    // y asi poder centrarla y manipular la visibilidad del resto de componentes del ViewGroup
    @Override
    public void setContentView(int layout){
        super.setContentView(layout);
        ViewGroup rootView = (ViewGroup) ((ViewGroup) this .findViewById(android.R.id.content)).getChildAt(0);
        progressBar.initControl(rootView);
        hideProgress();
    }

}
