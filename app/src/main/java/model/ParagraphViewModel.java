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
import com.example.nesti_mes_recettes.entity.Paragraph;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static kotlin.text.Typography.paragraph;

public class ParagraphViewModel extends AndroidViewModel {

    private MutableLiveData<List<Paragraph>> paragraph;

    public ParagraphViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<List<Paragraph>> getParagraph(int idRecipe) {
        if (paragraph == null) {
            paragraph = new MutableLiveData<List<Paragraph>>();
            loadIngredient(idRecipe);
        }
        return paragraph;
    }
    private void loadIngredient(int  idRecipe) {

        requestApi(idRecipe);

    }
    public void  requestApi(int idRecipe) {
        String token= "AKG123FKLQGF123KKHGJ123QSFD";
        String url = "https://delperie.needemand.com/realisations/Nesti_CodeIngniterdebug/public/index.php/"+token+"/api/paragraph/"+idRecipe;
        final RequestQueue request_queue = Volley.newRequestQueue(getApplication().getApplicationContext());
        JsonArrayRequest array_request = new JsonArrayRequest(
                url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ArrayList<Paragraph> paragraph = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object_JSON = response.getJSONObject(i);
                                Paragraph p = new Paragraph();

                                p.setContent(object_JSON.getString("content"));
                                p.setIdRecipe(Integer.parseInt(object_JSON.getString("idRecipe")));

                                Log.i("idRecipe :", object_JSON.getString("idRecipe"));

                                paragraph.add(p);

                            }


                        } catch (Exception e) {
                            Log.e("LogNesti", "Erreur de Convertion du Json");
                            e.printStackTrace();
                        }
                        ParagraphViewModel.this.paragraph.setValue(paragraph);
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
