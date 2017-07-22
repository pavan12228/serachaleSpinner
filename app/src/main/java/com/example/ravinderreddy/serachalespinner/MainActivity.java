package com.example.ravinderreddy.serachalespinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import java.util.ArrayList;
import java.util.List;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    SearchableSpinner searchableSpinner;
    TextView mTextView;
    List<Person> personArrayList = new ArrayList();
    private static final String Root_url = "https://api.myjson.com";
    WeatherAdapter weatherAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchableSpinner = (SearchableSpinner) findViewById(R.id.spinner);
        mTextView = (TextView) findViewById(R.id.text);
        insertUser();
    }


    private void insertUser() {

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Root_url).build();

        Apiservice api = adapter.create(Apiservice.class);
        api.apiDetails(new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {

                try {

                    JsonObject jsonObject1 = jsonObject.getAsJsonObject();
                    JsonArray jsonArray = jsonObject1.get("contacts").getAsJsonArray();

                    for (int i = 0; i < jsonArray.size(); i++) {


                        JsonObject jsonObject2 = jsonArray.get(i).getAsJsonObject();

                        String ids = jsonObject2.get("id").getAsString();
                        String names = jsonObject2.get("name").getAsString();
                        String emails = jsonObject2.get("email").getAsString();
                        String addresss = jsonObject2.get("address").getAsString();
                        String genders = jsonObject2.get("gender").getAsString();

                        Person person = new Person();
                        person.setId(ids);
                        person.setName(names);
                        personArrayList.add(person);
                    }


                } catch (JsonIOException e) {
                    e.printStackTrace();
                }


                weatherAdapter = new WeatherAdapter(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,personArrayList);
                searchableSpinner.setAdapter(weatherAdapter);
                searchableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                String text=adapterView.getItemAtPosition(i).toString();
                        mTextView.setText(text);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {


                    }
                });


            }


            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(MainActivity.this, "error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
