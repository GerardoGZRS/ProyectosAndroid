package mx.com.otss.c3.adaptores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mx.com.otss.c3.R;
import mx.com.otss.c3.model.Reglamentos;

public class ReglamentosCompletosAdapter extends RecyclerView.Adapter<ReglamentosCompletosAdapter.MyViewHolder> {

    private List<Reglamentos> rList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public ReglamentosCompletosAdapter(List<Reglamentos> moviesList) {
        this.rList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_reglamentos_completos, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Reglamentos r = rList.get(position);
        holder.title.setText(r.getNombreReglamento());
    }

    @Override
    public int getItemCount() {
        return rList.size();
    }
}
