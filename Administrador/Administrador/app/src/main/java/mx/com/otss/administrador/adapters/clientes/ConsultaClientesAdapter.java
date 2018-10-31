package mx.com.otss.administrador.adapters.clientes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.administrador.R;
import mx.com.otss.administrador.utils.Administrador;

public class ConsultaClientesAdapter extends RecyclerView.Adapter<ConsultaClientesAdapter.MyViewHolder> {
    private List<Administrador> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaClientes);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public ConsultaClientesAdapter(ArrayList<Administrador> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaClientesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_clientes, parent, false);
        return new ConsultaClientesAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaClientesAdapter.MyViewHolder holder, int position) {
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

