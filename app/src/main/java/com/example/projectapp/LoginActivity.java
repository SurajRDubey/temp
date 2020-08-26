package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectapp.databinding.ActivityLoginBinding;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;

    RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;

//    [
//    {
//        "macID": "1234567",
//            "available": false,
//            "_id": "5f41236b4a5e55028a1e2748",
//            "userName": "hello",
//            "password": "1234",
//            "__v": 0
//    }
//]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = activityLoginBinding.getRoot();
        setContentView(view);
        String mainUsersGetUrl = "https://google.com/api/iot?name="+activityLoginBinding.emailInputLogin.getText().toString()+"&pwd"+activityLoginBinding.passwordInputLogin.getText().toString();
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //no inits required


        activityLoginBinding.btnLoginGet.setOnClickListener(view1 -> {
            Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();

//            StringRequest request = new StringRequest(Request.Method.GET , mainUsersGetUrl ,
//                    response -> {
//
//
//
//
//            },error -> {}){
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String , String> userMap = new HashMap<>();
//                    userMap.put("userName" , Objects.requireNonNull(activityLoginBinding.emailInputLogin.getText()).toString().trim());
//                    userMap.put("password" , Objects.requireNonNull(activityLoginBinding.passwordInputLogin.getText()).toString().trim());
//                    return userMap;
//                }
//            };
//
//            requestQueue.add(request);
//            Toast.makeText(this, "set for verify", Toast.LENGTH_SHORT).show();



            jsonArrayRequest = new JsonArrayRequest(mainUsersGetUrl,response -> {
                JSONObject jsonObject = null;
                Log.d("h=login json","Hello : fromjson");
                for (int i=0;i< response.length();i++){


                    try {
                        jsonObject = response.getJSONObject(i);

                        if(jsonObject==null){
                            Toast.makeText(this, "Error Not Found", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else {
                            Toast.makeText(getApplicationContext(), "Code :" + jsonObject.getString("macID"), Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            } , error -> {
                Toast.makeText(this, "Error Cannot get user", Toast.LENGTH_SHORT).show();
            }




            );requestQueue.add(jsonArrayRequest);



//hora kya ? kya ara ?bol q nai ra


        }); //end clicker



   }
}
