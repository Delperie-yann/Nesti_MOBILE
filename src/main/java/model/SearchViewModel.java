package model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nesti_mes_recettes.entity.Search;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private MutableLiveData<List<Search>> search;

    public SearchViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Search>> getSearch(String name) {
        if (search == null) {
            search = new MutableLiveData<List<Search>>();
            loadSearch(name);
        }
        return search;
    }
    private void loadSearch(String  name) {

        requestApi(name);

    }
    public void  requestApi(String name) {
        String token= "AKG123FKLQGF123KKHGJ123QSFD";
        String url = "https://delperie.needemand.com/realisations/Nesti_CodeIngniterdebug/public/index.php/"+token+"/api/search/"+name;
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Search> search = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object_JSON = response.getJSONObject(i);
                                Search s = new Search();

                                s.setIdRecipe(object_JSON.getInt("idRecipe"));
                                s.setName(object_JSON.getString("name"));
                                search.add(s);

                            }

                            //  ListView list_View = getApplication()findViewById(R.id.gluten_listView);
                            //  RecipeAdapter adapter = new RecipeAdapter(GlutenActivity.this, android.R.layout.simple_list_item_1,recipes);
                            //  list_View.setAdapter(adapter);

                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de Convertion du Json");
                            e.printStackTrace();
                        }
                        SearchViewModel.this.search.setValue(search);
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(
                                getApplication().getApplicationContext(),
                                "Une erreur est survenue sur l'interrogation de l'API",
                                Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        request_queue.add(array_request);

    }


}

