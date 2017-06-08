package com.example.sistemas.webservicedatosabiertos.datosApi;

import com.example.sistemas.webservicedatosabiertos.models.Gobernacion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sistemas on 22/05/17.
 */

public interface DatosOpenAPIService {


    @GET("8fk7-6imt.json") //se modifica segun la pagina
    Call<ArrayList<Gobernacion>> obtenerListaReporteGobernacion();
}
