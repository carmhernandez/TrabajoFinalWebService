package com.example.sistemas.webservicedatosabiertos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



import com.example.sistemas.webservicedatosabiertos.datosApi.DatosOpenAPIService;
import com.example.sistemas.webservicedatosabiertos.models.Gobernacion;
import com.example.sistemas.webservicedatosabiertos.models.ListaGobernacionAdapter;



import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private static final String TAG = "GOBERNACION";
    private RecyclerView recyclerView;
    private boolean aptoParaCargar;
    private ListaGobernacionAdapter listaGobernacionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaGobernacionAdapter = new ListaGobernacionAdapter(this);
        recyclerView.setAdapter(listaGobernacionAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            obtenerLista();
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        obtenerLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opciones, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.uno) {
            Intent i = new Intent(this, Main2Activity.class );
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    private void obtenerLista() {

        DatosOpenAPIService service = retrofit.create(DatosOpenAPIService.class);
        Call<ArrayList<Gobernacion>> gobernacionRespuestaCall = service.obtenerListaReporteGobernacion();

        gobernacionRespuestaCall.enqueue(new Callback<ArrayList<Gobernacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Gobernacion>> call, Response<ArrayList<Gobernacion>> response) {
                if(response.isSuccessful()){
                    ArrayList lista = response.body();
                    listaGobernacionAdapter.adicionarListaGobernacion(lista);
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Gobernacion>> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
            }
        });

    }


}
