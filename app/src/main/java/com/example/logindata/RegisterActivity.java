package com.example.logindata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    EditText username;
    EditText number;
    EditText  email;
    EditText password;
    EditText zipcode;
    MaterialButton done;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    String json;

   public ArrayList<DetailsModel> details;
    Gson gson = new Gson();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");
        username = findViewById(R.id.username);
        number = findViewById(R.id.number_edit_text);
        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        zipcode = findViewById(R.id.zipcode_edit_text);
        done = findViewById(R.id.done_button);




        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        saveData(username.getText().toString(), password.getText().toString(), number.getText().toString(), email.getText().toString(), zipcode.getText().toString());
            }

        });



        getUserListFromSharedPrefrenace();

    }

    private void getUserListFromSharedPrefrenace()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        String list = sharedPreferences.getString("userdata",null);
        Type type = new TypeToken<ArrayList<DetailsModel>>(){}.getType();
        details = gson.fromJson(list, type);

        Log.e("user ist",details.toString());
    }

    public void saveData(String name, String password, String number, String email, String zipcode) {
       if(details == null ){
           details = new ArrayList<>();
       }
            sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
           SharedPreferences.Editor myEdit = sharedPreferences.edit();


               Gson gson = new Gson();



               json = gson.toJson(details);




               if(json.contains(email) || json.contains(number)){
                   Toast.makeText(this, "exist", Toast.LENGTH_SHORT).show();
               }else {
                   details.add(new DetailsModel(name, password, number, email, zipcode));
                   json = gson.toJson(details);
                   myEdit.putString("userdata", json);


                   myEdit.apply();
                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                   startActivity(intent);
               }
           }

    }

