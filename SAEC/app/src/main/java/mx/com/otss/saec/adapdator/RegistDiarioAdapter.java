package mx.com.otss.saec.adapdator;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.otss.saec.R;
import mx.com.otss.saec.usuarios.Tabla_Alumnos;

public class RegistDiarioAdapter extends RecyclerView.Adapter<RegistDiarioAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Tabla_Alumnos> persons;
    private RegistDiarioAdapter.CustomFilter mFilter;

    private ArrayList<Tabla_Alumnos> personsFilter;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idnivelUno;
        public TextView txtNombre;
        private TextView horaEntrada;
        private TextView horaSalida;


        public MyViewHolder(View view) {
            super(view);
            idnivelUno=(TextView) view.findViewById(R.id.txtMatricula);
            txtNombre = (TextView) view.findViewById(R.id.txtNombreNU);
            horaEntrada = (TextView)view.findViewById(R.id.txtHoraEntrada);
            horaSalida = (TextView)view.findViewById(R.id.txtHoraSalida);
        }
    }


    public RegistDiarioAdapter(ArrayList<Tabla_Alumnos> persons){
        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(RegistDiarioAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public RegistDiarioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_registro_diario, parent, false);
        return new RegistDiarioAdapter.MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(RegistDiarioAdapter.MyViewHolder holder, int position) {
        Tabla_Alumnos nu = personsFilter.get(position);
        holder.idnivelUno.setText(nu.getIdAlumno());
        holder.txtNombre.setText(nu.getDia());
        holder.horaEntrada.setText(nu.getHora_entrada());
        holder.horaSalida.setText(nu.getHora_salida());
    }

    @Override
    public int getItemCount() {

        Log.i("Todal del array:", "" + personsFilter.size());
        return personsFilter.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private RegistDiarioAdapter listAdapter;

        private CustomFilter(RegistDiarioAdapter listAdapter) {
            super();
            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            personsFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                personsFilter.addAll(persons);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Tabla_Alumnos person : persons) {
                    if (person.getIdAlumno().toLowerCase().contains(filterPattern)) {
                        personsFilter.add(person);
                    } else if(person.getHora_entrada().toLowerCase().contains(filterPattern)){
                        personsFilter.add(person);
                    } else if(person.getHora_salida().toLowerCase().contains(filterPattern)){
                        personsFilter.add(person);
                    } else if(person.getDia().toLowerCase().contains(filterPattern)){
                        personsFilter.add(person);
                    }
                }
            }
            results.values = personsFilter;
            results.count = personsFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.listAdapter.notifyDataSetChanged();
        }

    }
}
