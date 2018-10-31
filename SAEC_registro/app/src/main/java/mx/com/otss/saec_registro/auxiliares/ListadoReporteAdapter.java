package mx.com.otss.saec_registro.auxiliares;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.com.otss.saec_registro.R;

public class ListadoReporteAdapter extends RecyclerView.Adapter<ListadoReporteAdapter.MyViewHolder> {

    private List<AlumnoRegistro> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.id);

        }
    }


    public ListadoReporteAdapter(List<AlumnoRegistro> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_reporte, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        AlumnoRegistro movie = moviesList.get(position);
        String cadena = movie.getMatricula() + " " + movie.getNombreAlummo() + " " + movie.getDia();
        holder.title.setText(cadena);

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
