package mx.com.otss.saec.adapdator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.otss.saec.R;
import mx.com.otss.saec.usuarios.Tabla_Alumnos;

public class RegistroDiarioAlumnoAdapter extends RecyclerView.Adapter<RegistroDiarioAlumnoAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Tabla_Alumnos> ArrayList_filtrar_alumnos;
    private ArrayList<Tabla_Alumnos> ArrayList_Alumnos;
    private RegistroDiarioAlumnoAdapter.CustomFilter mFilter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idnivelUno;
        public TextView txtNombre;
        public TextView horaEntrada;
        public TextView horaSalida;

        public MyViewHolder(View view) {
            super(view);
            idnivelUno=(TextView) view.findViewById(R.id.txtMatricula);
            txtNombre = (TextView) view.findViewById(R.id.txtNombreNU);
            horaEntrada = (TextView)view.findViewById(R.id.txtHoraEntrada);
            horaSalida = (TextView)view.findViewById(R.id.txtHoraSalida);
        }
    }


    public RegistroDiarioAlumnoAdapter(ArrayList<Tabla_Alumnos> alumnos){
        this.ArrayList_filtrar_alumnos = alumnos;
        this.ArrayList_Alumnos = new ArrayList<>();
        this.ArrayList_Alumnos.addAll(alumnos);
        this.mFilter = new CustomFilter(RegistroDiarioAlumnoAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }


    @Override
    public RegistroDiarioAlumnoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_registroxalumno, parent, false);

        return new RegistroDiarioAlumnoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RegistroDiarioAlumnoAdapter.MyViewHolder holder, int position) {
        Tabla_Alumnos nu = ArrayList_Alumnos.get(position);
       holder.idnivelUno.setText(nu.getIdAlumno());
        holder.txtNombre.setText(nu.getDia());
        holder.horaEntrada.setText(nu.getHora_entrada());
       holder.horaSalida.setText(nu.getHora_salida());


    }

    @Override
    public int getItemCount() {
        return ArrayList_Alumnos.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private RegistroDiarioAlumnoAdapter listAdapter;

        private CustomFilter(RegistroDiarioAlumnoAdapter listAdapter) {
            super();
            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList_Alumnos.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                ArrayList_Alumnos.addAll(ArrayList_filtrar_alumnos);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final Tabla_Alumnos person : ArrayList_filtrar_alumnos) {
                    if (person.getIdAlumno().toLowerCase().contains(filterPattern)) {
                        ArrayList_Alumnos.add(person);
                    } else if(person.getHora_entrada().toLowerCase().contains(filterPattern)){
                        ArrayList_Alumnos.add(person);
                    } else if(person.getHora_salida().toLowerCase().contains(filterPattern)){
                        ArrayList_Alumnos.add(person);
                    } else if(person.getDia().toLowerCase().contains(filterPattern)){
                        ArrayList_Alumnos.add(person);
                    }
                }
            }
            results.values = ArrayList_Alumnos;
            results.count = ArrayList_Alumnos.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.listAdapter.notifyDataSetChanged();
        }

    }
}

