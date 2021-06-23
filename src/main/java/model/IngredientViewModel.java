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
import com.example.nesti_mes_recettes.entity.Ingredient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IngredientViewModel extends AndroidViewModel {

    private MutableLiveData<List<Ingredient>> ingredients;

    public IngredientViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Ingredient>> getIngredient(int idRecipe) {
        if (ingredients == null) {
            ingredients = new MutableLiveData<List<Ingredient>>();
            loadIngredient(idRecipe);
        }
        return ingredients;
    }
    private void loadIngredient(int  idRecipe) {

        requestApi(idRecipe);

    }
    public void  requestApi(int idRecipe) {

        String token= "AKG123FKLQGF123KKHGJ123QSFD";
        String url = "https://delperie.needemand.com/realisations/Nesti_CodeIngniterdebug/public/index.php/"+token+"/api/ingredient/"+idRecipe;
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Ingredient> ingredient = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object_JSON = response.getJSONObject(i);
                                Ingredient r = new Ingredient();
                                r.setQuantity(object_JSON.getString("quantity"));
                                r.setNameProduct(object_JSON.getString("nameProduct"));
                                r.setIdRecipe(Integer.parseInt(object_JSON.getString("idRecipe")));
                                r.setIdProduct(Integer.parseInt(object_JSON.getString("idProduct")));
                                Log.i("idRecipe :", object_JSON.getString("idProduct"));
                                r.setUnitName(object_JSON.getString("unitName"));
                                ingredient.add(r);

                            }

                            //  ListView list_View = getApplication()findViewById(R.id.gluten_listView);
                            //  RecipeAdapter adapter = new RecipeAdapter(GlutenActivity.this, android.R.layout.simple_list_item_1,recipes);
                            //  list_View.setAdapter(adapter);

                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de Convertion du Json");
                            e.printStackTrace();
                        }
                        IngredientViewModel.this.ingredients.setValue(ingredient);
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

