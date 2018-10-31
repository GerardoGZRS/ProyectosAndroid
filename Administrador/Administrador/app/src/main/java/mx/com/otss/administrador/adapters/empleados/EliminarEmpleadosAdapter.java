package mx.com.otss.administrador.adapters.empleados;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.administrador.R;
import mx.com.otss.administrador.utils.Administrador;

public class EliminarEmpleadosAdapter extends RecyclerView.Adapter<EliminarEmpleadosAdapter.MyViewHolder> {
    private List<Administrador> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtEliminarEmpleados);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public EliminarEmpleadosAdapter(ArrayList<Administrador> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public EliminarEmpleadosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_eliminar_empleados, parent, false);
        return new EliminarEmpleadosAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(EliminarEmpleadosAdapter.MyViewHolder holder, int position) {
        Administrador movie = moviesList.get(position);



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