package mx.com.otss.c3.adaptores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.otss.c3.R;
import mx.com.otss.c3.model.NivelDos;

public class NivelDosAdapter extends RecyclerView.Adapter<NivelDosAdapter.MyViewHolder> implements Filterable {
    private ArrayList<NivelDos> persons;
    private ArrayList<NivelDos> personsFilter;
    private NivelDosAdapter.CustomFilter mFilter;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView idnivelUno, txtNombre;

    public MyViewHolder(View view) {
        super(view);
        idnivelUno = (TextView) view.findViewById(R.id.txtIdND);
        txtNombre = (TextView) view.findViewById(R.id.txtNombreND);
    }
}

    public NivelDosAdapter(ArrayList<NivelDos> persons) {
        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(NivelDosAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nivel_dos_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NivelDos nu = personsFilter.get(position);
        String id = String.valueOf(nu.getIdNivelDos());
        holder.idnivelUno.setText(id);
        holder.txtNombre.setText(nu.getNombreIndex());
    }

    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private NivelDosAdapter listAdapter;

        private CustomFilter(NivelDosAdapter listAdapter) {
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
                for (final NivelDos person : persons) {
                    if (person.getNombreIndex().toLowerCase().contains(filterPattern)) {
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
