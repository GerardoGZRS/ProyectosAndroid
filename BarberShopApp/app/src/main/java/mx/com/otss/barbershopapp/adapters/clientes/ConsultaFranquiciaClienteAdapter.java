package mx.com.otss.barbershopapp.adapters.clientes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.utils.BarberShop;

public class ConsultaFranquiciaClienteAdapter extends RecyclerView.Adapter<ConsultaFranquiciaClienteAdapter.MyViewHolder> {
    private List<BarberShop> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaFranquiciaCliente);
        }
    }

    /**
     * @param moviesList de tipo List
     */
    public ConsultaFranquiciaClienteAdapter(ArrayList<BarberShop> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaFranquiciaClienteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_franquicia_cliente, parent, false);
        return new ConsultaFranquiciaClienteAdapter.MyViewHolder(itemView);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaFranquiciaClienteAdapter.MyViewHolder holder, int position) {
        BarberShop movie = moviesList.get(position);
        String cadena = " " + movie.getIdFranquisia() + " Nombre: " +  movie.getNombreFranquisia() + " Direccion:"+ movie.getDireccionFranquisia();
        holder.title.setText(cadena);

    }

    /**
     * @return movieList
     */
    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}