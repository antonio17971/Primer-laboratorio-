package com.example.adrian.mobile.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.adrian.mobile.Models.CarreraModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.adrian.mobile.AccesoDatos.Model;
import com.example.adrian.mobile.Adapter.CarreraAdapter;
import com.example.adrian.mobile.Helper.RecyclerItemTouchHelper;
import com.example.adrian.mobile.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AdmCarreraActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, CarreraAdapter.CarreraAdapterListener {

    private RecyclerView mRecyclerView;
    private CarreraAdapter mAdapter;
    private List<CarreraModel> carreraList;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private Model model;
    private static final String URL = "http://192.168.0.119:8080/ServerWeb/listarCarreras";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_carrera);
        Toolbar toolbar = findViewById(R.id.toolbarCa);
        setSupportActionBar(toolbar);


        //toolbar fancy stuff
      //  getSupportActionBar().setTitle(getString(R.string.my_carrera));

        mRecyclerView = findViewById(R.id.recycler_carreraFld);
        carreraList = new ArrayList<>();
        model = Model.getInstance();
        GetCarreras getLista = new GetCarreras(this.URL, this);
        getLista.execute();


        // go to update or add career
        fab = findViewById(R.id.addBtnCa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdCarrera();
            }
        });

        //delete swiping left and right
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        //should use database info


        // Receive the CarreraModel sent by AddUpdCarreraActivity
        checkIntentInformation();

        //refresh view

    }

    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CarreraModel aux;
            aux = (CarreraModel) getIntent().getSerializableExtra("addCarrera");
            if (aux == null) {
                aux = (CarreraModel) getIntent().getSerializableExtra("editCarrera");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
//                    for (CarreraModel carrera : carreraList) {
//                        if (carrera.getCodigo() == aux.getCodigo()) {
//                            carrera.setNombre(aux.getNombre());
//                            carrera.setTitulo(aux.getTitulo());
//                            founded = true;
//                            break;
//                        }
//                    }
                    //check if exist
                    if (founded) {
                        //Toast.makeText(getApplicationContext(), aux.getNombre() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(getApplicationContext(), aux.getNombre() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new CarreraModel Object
                //carreraList.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void goToAddUpdCarrera() {
        Intent intent = new Intent(this, AddUpdCarreraActivity.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof CarreraAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = carreraList.get(viewHolder.getAdapterPosition()).getNombre();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item from adapter
                        mAdapter.restoreItem(deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        } else {
            //If is editing a row object
            CarreraModel aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, AddUpdCarreraActivity.class);
            intent.putExtra("editable", true);
            intent.putExtra("carrera", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds carreraList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(CarreraModel carrera) { //TODO get the select item of recycleView
        Toast.makeText(getApplicationContext(), "Selected: " + carrera.getCodigo() + ", " + carrera.getNombre(), Toast.LENGTH_LONG).show();
    }

    class GetCarreras extends AsyncTask<URL, Integer, String> {


        private String apiUrl;
        private AdmCarreraActivity activity;
        public GetCarreras(String apiUrl, AdmCarreraActivity activity) {
            super ();
            this.apiUrl = apiUrl;
            this.activity = activity;
        }



        protected String doInBackground(URL... urls) {
            URL url ;
            HttpURLConnection urlConnection = null;
            String json = "";
            try {
                url = new URL(this.apiUrl);
                urlConnection =  (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");


                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in,"UTF-8");
                BufferedReader stringBufer= new BufferedReader(isw);

                String data = "";
                while ((data = stringBufer.readLine()) != null)
                    json +=data;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return json;

        }

        protected void onProgressUpdate(Integer... progress) {
            // optionally report progress
        }

        protected void onPostExecute(String result) {
            JSONArray jsonArray = null;
            Gson gson = new Gson();
            ArrayList<CarreraModel> carreras = new ArrayList<>();
            try {
                jsonArray = new JSONArray(result);
                for (int i=0;i<jsonArray.length();i++){
                    //carreras.add( gson.fromJson(jsonArray.getString(i), CarreraModel.class));
                    carreras.add(gson.fromJson(jsonArray.getString(i), CarreraModel.class));
                }
                model.setCarreras(carreras);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            carreraList = model.getCarreras();
            mAdapter = new CarreraAdapter(carreraList, this.activity);
            coordinatorLayout = findViewById(R.id.coordinator_layoutCa);

            // white background notification bar
            whiteNotificationBar(mRecyclerView);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this.activity, DividerItemDecoration.VERTICAL));
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

}
