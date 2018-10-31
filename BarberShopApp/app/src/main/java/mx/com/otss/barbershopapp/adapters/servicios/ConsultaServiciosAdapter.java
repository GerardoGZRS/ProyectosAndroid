package mx.com.otss.barbershopapp.adapters.servicios;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.com.otss.barbershopapp.R;
import mx.com.otss.barbershopapp.utils.BarberShop;

public class ConsultaServiciosAdapter extends RecyclerView.Adapter<ConsultaServiciosAdapter.MyViewHolder> {
    private List<BarberShop> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;
       public ImageView imageView;
        /**
         *
         * @param view de tipo View
         */
        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txtConsultaServicio);
            imageView = (ImageView) view.findViewById(R.id.imageView2);
        }
    }

    /**
     *
     * @param moviesList de tipo List
     */
    public ConsultaServiciosAdapter(ArrayList<BarberShop> moviesList) {
        this.moviesList = moviesList;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return new View
     */
    @Override
    public ConsultaServiciosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_consulta_servicios, parent, false);
        return new ConsultaServiciosAdapter.MyViewHolder(itemView);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ConsultaServiciosAdapter.MyViewHolder holder, int position) {
        BarberShop movie = moviesList.get(position);
        String cadena = "Nombre del servico: " + movie.getNombreServicio() + " Precio del servicio: " + movie.getPrecio() + " Tiempo Requerido: "
                        + movie.getTiempoRequerido() + "";
        holder.title.setText(cadena);
        Log.i("Info", "imagen 64" + movie.getImagen());
        Bitmap bitmap = StringToBitMap(movie.getImagen());
        holder.imageView.setImageBitmap(bitmap);
    }

    /**
     *
     * @return movieList
     */

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
