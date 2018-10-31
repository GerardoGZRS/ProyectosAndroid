package mx.com.otss.barbershopapp.adapters.comisiones;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.utils.BarberShop;

public class ConsultaEmpleadosComisionAdapter extends RecyclerView.Adapter<ConsultaEmpleadosComisionAdapter.MyViewHolder> {
    private List<BarberShop> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaComisionEmpleado);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public ConsultaEmpleadosComisionAdapter(ArrayList<BarberShop> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaEmpleadosComisionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_empleado_comision, parent, false);
        return new ConsultaEmpleadosComisionAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaEmpleadosComisionAdapter.MyViewHolder holder, int position) {
        BarberShop movie = moviesList.get(position);
        String cadena = "Nombre Empleado: " + movie.getNombreEmpleado() + " " + movie.getApellidoPaterno() + " " +  movie.getApellidoMaterno();
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
