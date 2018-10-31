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

public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.MyViewHolder>implements Filterable {

    private ArrayList<Tabla_Alumnos> persons;
    private CampusAdapter.CustomFilter mFilter;
    private ArrayList<Tabla_Alumnos> personsFilter;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idnivelUno;
        public TextView txtNombre;

        public MyViewHolder(View view) {
            super(view);
            idnivelUno= (TextView)view.findViewById(R.id.txtMatricula);
            txtNombre = (TextView) view.findViewById(R.id.txtNombreNU);

        }
    }

    public CampusAdapter(ArrayList<Tabla_Alumnos> persons){
        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(CampusAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_campus, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Tabla_Alumnos nu = personsFilter.get(position);
                holder.idnivelUno.setText(nu.getIdAlumno());
                holder.txtNombre.setText(nu.getNombreAlumno());
    }

    @Override
    public int getItemCount() {
        Log.i("Todal del array:", "" + personsFilter.size());
        return personsFilter.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private CampusAdapter listAdapter;

        private CustomFilter(CampusAdapter listAdapter) {
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
                    }  else if(person.getNombreAlumno().toLowerCase().contains(filterPattern)){
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
