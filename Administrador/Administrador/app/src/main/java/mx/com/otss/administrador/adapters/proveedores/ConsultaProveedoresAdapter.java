package mx.com.otss.administrador.adapters.proveedores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.administrador.R;
import mx.com.otss.administrador.utils.Administrador;

public class ConsultaProveedoresAdapter extends RecyclerView.Adapter<ConsultaProveedoresAdapter.MyViewHolder> {
    private List<Administrador> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaProveedores);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public ConsultaProveedoresAdapter(ArrayList<Administrador> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaProveedoresAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_proveedores, parent, false);
        return new ConsultaProveedoresAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaProveedoresAdapter.MyViewHolder holder, int position) {
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