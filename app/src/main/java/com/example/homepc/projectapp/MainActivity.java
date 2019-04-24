package com.example.homepc.projectapp;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import java.util.*;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    Button b1;
    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et1 = (EditText) findViewById(R.id.et1);
                final String device_id = et1.getText().toString();
                System.out.println(device_id);
                StringRequest stringRequest;

                String url = "http://192.168.43.128:5000/beach"; // This is the localhost to Flask

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                stringRequest = new StringRequest(
                        Request.Method.POST,
                        url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                String arr[] = new String[20];
                                int i = 0;
                                String temp = "";
                                try {
                                    JSONObject tokenize = new JSONObject(response);
                                    String token = tokenize.getString("out");
                                    token = token.substring(1,token.length()-1);
                                    System.out.println(token);
                                    StringTokenizer tokenizer = new StringTokenizer(token, ",");
                                    while (tokenizer.hasMoreTokens()) {
                                        if(i>4) {
                                            temp = tokenizer.nextToken();
                                            temp = temp.substring(1,temp.length()-1);
                                            arr[i++] = temp;
                                        }
                                        else
                                            arr[i++] = tokenizer.nextToken();
                                    }
                                    for(int j=0;j<10;j++)
                                        System.out.println(arr[j]);
                                    Toast.makeText(MainActivity.this, "Done till here!", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(MainActivity.this, Checker.class);
                                    in.putExtra("device_id", device_id);
                                    in.putExtra("WaterTemperature", arr[0]);
                                    in.putExtra("Turbidity", arr[1]);
                                    in.putExtra("WaveHeight", arr[2]);
                                    in.putExtra("WavePeriod", arr[3]);
                                    in.putExtra("BatteryLife", arr[4]);
                                    in.putExtra("WaterTemperature_th", arr[5]);
                                    in.putExtra("Turbidity_th", arr[6]);
                                    in.putExtra("WaveHeight_th", arr[7]);
                                    in.putExtra("WavePeriod_th", arr[8]);
                                    in.putExtra("BatteryLife_th", arr[9]);
                                    startActivity(in);
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> id = new HashMap<String, String>();
                            id.put("device_id", device_id);
                            return id;
                        }
                };

                int socketTimeout = 30000; // 30 seconds. You can change it
                RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

                stringRequest.setRetryPolicy(policy);

                queue.add(stringRequest);

            }
        });
    }
}
