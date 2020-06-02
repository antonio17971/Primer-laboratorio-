package com.example.adrian.mobile.Adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.adrian.mobile.Models.CarreraModel;
import com.example.adrian.mobile.R;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class CarreraAdapter extends RecyclerView.Adapter<CarreraAdapter.MyViewHolder> implements Filterable {
    private List<CarreraModel> carreraList;
    private List<CarreraModel> carreraListFiltered;
    private CarreraAdapterListener listener;
    private CarreraModel deletedItem;
    private  static final  String URL_DELETE =  "http://192.168.0.119:8080/ServerWeb/borrarCarrera?ID=%s";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(carreraListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }


    public CarreraAdapter(List<CarreraModel> carreraList, CarreraAdapterListener listener) {
        this.carreraList = carreraList;
        this.listener = listener;
        //init filter
        this.carreraListFiltered = carreraList;
    }

    @Override
    public CarreraAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // basically a render
        final CarreraModel carrera = carreraListFiltered.get(position);
        holder.titulo1.setText(String.valueOf(carrera.getCodigo()));
        holder.titulo2.setText(carrera.getNombre());
        holder.description.setText(carrera.getTitulo());
    }
    @Override
    public int getItemCount() {
        return carreraListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = carreraListFiltered.remove(position);
        Iterator<CarreraModel> iter = carreraList.iterator();
        DeleteCarrera carrera;
        while (iter.hasNext()) {
            CarreraModel aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
                carrera = new DeleteCarrera(URL_DELETE,deletedItem.getCodigo());
                carrera.execute();
                //deletedItem.getCodigo();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (carreraListFiltered.size() == carreraList.size()) {
            carreraListFiltered.add(position, deletedItem);
        } else {
            carreraListFiltered.add(position, deletedItem);
            carreraList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public CarreraModel getSwipedItem(int index) {
        if (this.carreraList.size() == this.carreraListFiltered.size()) { //not filtered yet
            return carreraList.get(index);
        } else {
            return carreraListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (carreraList.size() == carreraListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(carreraList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(carreraList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(carreraListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(carreraListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    carreraListFiltered = carreraList;
                } else {
                    List<CarreraModel> filteredList = new ArrayList<>();
                    for (CarreraModel row : carreraList) {
                        // filter use two parameters
                        if (String.valueOf(row.getCodigo()).toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    carreraListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = carreraListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                carreraListFiltered = (ArrayList<CarreraModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CarreraAdapterListener {
        void onContactSelected(CarreraModel carrera);
    }

    class DeleteCarrera extends AsyncTask<String, Integer, String> {
        private String apiUrl;
        private int codigo;
        public DeleteCarrera(String URL, int codigo){
            this.apiUrl= URL;
            this.codigo = codigo;
        }
        protected String doInBackground(String... urls) {
            URL url ;
            HttpURLConnection urlConnection = null;
            String query ="";
            try {
                query = String.format(apiUrl, this.codigo);
                url = new URL(query);
                urlConnection =  (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("DELETE");
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line = "";
                    while ((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                } else {
                    return new String("false : " + responseCode);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            // optionally report progress
        }

        protected void onPostExecute(String result) {
            // do something on the UI thread
        }
    }

}
