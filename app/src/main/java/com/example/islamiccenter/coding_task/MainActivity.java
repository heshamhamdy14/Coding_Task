package com.example.islamiccenter.coding_task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
import com.example.islamiccenter.coding_task.data.ProductData;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    GridView product_list;
    product_adapter product_adapter;
    Gson gson=new Gson();
    ProductData[]ProductData;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product_list= (GridView) findViewById(R.id.gridview);

        String url="https://grapesnberries.getsandbox.com/products?count=10&from=1";
        Log.d("zamel", url);




        get_products get_products=new get_products();
                get_products.execute(url);
    }




//    private void executeWebService(/*String url*/) {
//
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            JSONArray jsonArray = jsonObject.getJSONArray("results");
//
//
//                            ProductData = gson.fromJson(jsonArray.toString(), ProductData[].class);
//                            //  Toast.makeText(MainActivity.this, movieDataModels[0].getOverview(), Toast.LENGTH_SHORT).show();
//
//                            adapter = new product_adapter( MainActivity.this , ProductData);
//                            product_list.setAdapter(adapter);
//                            product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                     Toast.makeText(MainActivity.this, "product", Toast.LENGTH_SHORT).show();
//
//                                }
//                            });
//
//
//                        } catch (JsonIOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//    }

         class get_products extends AsyncTask<String , Void ,ProductData[]>
    {
        protected void onPreExecute() {

            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("loading.....");
            progressDialog.show();
        }

        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();
        }
        @Override
        protected ProductData[] doInBackground(String... url) {
            try {
                String s = run(url[0]);
                Log.d("hesham", s);
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = jsonObject.getJSONArray(null);

                ProductData = gson.fromJson(jsonArray.toString(), ProductData[].class);
                Log.d("zamel", "doInBackground: "+ ProductData.length);
                return ProductData;

            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(ProductData[] productDatas) {

            progressDialog.dismiss();
            if(productDatas != null) {
                product_adapter = new product_adapter(MainActivity.this, ProductData);
                product_list.setAdapter(product_adapter);
                product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Toast.makeText(MainActivity.this, "product clicked", Toast.LENGTH_SHORT).show();

                    }
                });

            }

        }
    }
}
