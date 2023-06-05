package com.example.logindata;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText username;
   TextInputEditText password;
    MaterialButton next;
    MaterialButton register;
    ArrayList<DetailsModel> details ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password_edit_text);
        register = findViewById(R.id.register_button);
        next = findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = sharedPreferences.getString("userdata",null);

              Type type = new TypeToken<ArrayList<DetailsModel>>(){

                }.getType();
                details = gson.fromJson(json,type);





                     if (json.contains(username.getText().toString()) && json
                              .contains(password.getText().toString())) {

                         myEdit.putBoolean("hasloggedin",true);
                         myEdit.apply();
                          Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                          startActivity(intent);
                      } else {
                          Toast.makeText(LoginActivity.this, "invalid username or password", Toast.LENGTH_SHORT).show();
                         myEdit.putBoolean("hasloggedin",false);
                         myEdit.apply();

                      }


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

}