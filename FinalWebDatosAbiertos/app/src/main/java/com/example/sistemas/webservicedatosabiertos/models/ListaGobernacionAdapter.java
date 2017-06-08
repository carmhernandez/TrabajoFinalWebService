package com.example.sistemas.webservicedatosabiertos.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.webservicedatosabiertos.R;

import java.util.ArrayList;

/**
 * Created by sistemas on 7/06/17.
 */

public class ListaGobernacionAdapter extends RecyclerView.Adapter<ListaGobernacionAdapter.ViewHolder> {

    private ArrayList<Gobernacion> dataset;

    private Context context;

    public ListaGobernacionAdapter (Context context) {
        this.context = context;
        dataset = new ArrayList<>();

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gov, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Gobernacion a = dataset.get(position);
        holder.ips.setText(a.getIps());
        holder.marca.setText(a.getMarca().toString());
        holder.modelo.setText(a.getModelo().toString());
        holder.placa.setText(a.getPlaca().toString());
        holder.propiedad.setText(a.getPropiedad().toString());
        holder.tipo.setText(a.getTipo().toString());


    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void adicionarListaGobernacion(ArrayList<Gobernacion> listaGobernacion) {
        dataset.addAll(listaGobernacion);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView ips;
        private TextView marca;
        private TextView modelo;
        private TextView placa;
        private TextView propiedad;
        private TextView tipo;

        public ViewHolder(View itemView) {
            super(itemView);


            ips = (TextView) itemView.findViewById(R.id.ips);
            marca= (TextView) itemView.findViewById(R.id.marca);
            modelo=(TextView) itemView.findViewById(R.id.modelo);
            placa=(TextView) itemView.findViewById(R.id.placa);
            propiedad=(TextView) itemView.findViewById(R.id.propiedad);
            tipo=(TextView) itemView.findViewById(R.id.tipo);
        }
    }

}
