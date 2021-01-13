package com.example.vroomrr.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroomrr.Cryptography;
import com.example.vroomrr.MainActivity;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.Session;
import com.example.vroomrr.User;
import com.example.vroomrr.ui.register.RegisterActivity;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements ServerCallback {
    private Button btn;
    private EditText usernameTxt;
    private EditText passwordTxt;
    private TextView registerTxt;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences SP = Cryptography.getEncryptedSharedPreferences(this);
        if(SP.contains(String.valueOf(R.string.SessionId))){
            loginUser(new User());
        }

        setContentView(R.layout.fragment_login);
        setupView();
        setOnclickListeners();
    }

    public void setupView() {
        btn = findViewById(R.id.button_login);
        usernameTxt = findViewById(R.id.username_login);
        passwordTxt = findViewById(R.id.password_login);
        registerTxt = findViewById(R.id.register_text);
        gson = new Gson();
    }

    public void setOnclickListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userObject = new User();

                if(!usernameTxt.getText().toString().equals("") && !passwordTxt.getText().toString().equals("")) {
                    userObject.setUsername(usernameTxt.getText().toString());
                    userObject.setPassword(passwordTxt.getText().toString());

                    loginUser(userObject);
                } else {
                    //TODO: Error Handling
                    System.out.println("Text = Null");
                }
            }
        });

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loginUser(User body) {
        ServerConnection.login(body, this, this);
    }

    @Override
    public void completionHandler(String object, String url) {
        Session session = gson.fromJson(object, Session.class);

        Cryptography.addToSharedPreferences(this, String.valueOf(R.string.SessionId), session.getSession_id());
        Cryptography.addToSharedPreferences(this, "UserId", session.getUser_id());

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
