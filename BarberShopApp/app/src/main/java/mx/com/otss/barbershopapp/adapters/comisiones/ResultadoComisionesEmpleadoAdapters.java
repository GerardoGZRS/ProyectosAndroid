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

public class ResultadoComisionesEmpleadoAdapters extends RecyclerView.Adapter<ResultadoComisionesEmpleadoAdapters.MyViewHolder> {
private List<BarberShop> moviesList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView title, year, genre;

    /**
     *
     * @param view de tipo View
     */
    public MyViewHolder(View view) {
        super(view);
        title = (TextView) view.findViewById(R.id.txtConsultaResultadoComisionEmpleado);
    }
}

    /**
     *
     * @param moviesList de tipo List
     */
    public ResultadoComisionesEmpleadoAdapters(ArrayList<BarberShop> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ResultadoComisionesEmpleadoAdapters.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_resultado_comision_empleado, parent, false);
        return new ResultadoComisionesEmpleadoAdapters.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ResultadoComisionesEmpleadoAdapters.MyViewHolder holder, int position) {
        BarberShop movie = moviesList.get(position);
        String cadena = "Tipo Comisión: " + movie.getTipoComision() + ",  Valor Comisión: " + movie.getValorComision() +
                ", Id Empleado: " + movie.getIdEmpleados();
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
