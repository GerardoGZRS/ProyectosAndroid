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
import mx.com.otss.c3.model.NivelUno;

public class NivelUnoAdapter extends RecyclerView.Adapter<NivelUnoAdapter.MyViewHolder> implements Filterable {
    private ArrayList<NivelUno> persons;
    private ArrayList<NivelUno> personsFilter;
    private CustomFilter mFilter;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView idnivelUno, txtNombre;

        public MyViewHolder(View view) {
            super(view);
            idnivelUno = (TextView) view.findViewById(R.id.txtIdNU);
            txtNombre = (TextView) view.findViewById(R.id.txtNombreNU);
        }
    }

    public NivelUnoAdapter(ArrayList<NivelUno> persons) {
        this.persons = persons;
        this.personsFilter = new ArrayList<>();
        this.personsFilter.addAll(persons);
        this.mFilter = new CustomFilter(NivelUnoAdapter.this);
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nivel_uno_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NivelUno nu = personsFilter.get(position);
        String id = String.valueOf(nu.getIdNivelUno());
        holder.idnivelUno.setText(id);
        holder.txtNombre.setText(nu.getNombre());
        ;
    }

    @Override
    public int getItemCount() {
        return personsFilter.size();
    }

    /*Filtro*/
    public class CustomFilter extends Filter {
        private NivelUnoAdapter listAdapter;

        private CustomFilter(NivelUnoAdapter listAdapter) {
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
                for (final NivelUno person : persons) {
                    if (person.getNombre().toLowerCase().contains(filterPattern)) {
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
