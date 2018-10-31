package mx.com.otss.barbershopapp.adapters.pagos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.utils.BarberShop;

public class ConsultaPagoCitaAdapter extends RecyclerView.Adapter<ConsultaPagoCitaAdapter.MyViewHolder> {
    private List<BarberShop> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaCitasPago);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public ConsultaPagoCitaAdapter(ArrayList<BarberShop> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaPagoCitaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_cita_pago, parent, false);
        return new ConsultaPagoCitaAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaPagoCitaAdapter.MyViewHolder holder, int position) {
        BarberShop movie = moviesList.get(position);
        String cadena = "Nombre Cliente: " + movie.getNombreCliente() + ", Nombre del Empleado: " + movie.getNombreEmpleado() + ", Nombre del servicio: "
                + movie.getNombreServicio() + ", Nombre del Franquicia: " + movie.getNombreFranquisia() + ", Fecha de la cita: " + movie.getFechaInicio();
        holder.title.setText(cadena);

    }

    /**
     *
     * @return movieList
     */
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
