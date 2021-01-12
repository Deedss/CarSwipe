package com.example.vroomrr.ui.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.User;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements ServerCallback {
    private Button btn;
    private TextView nameTxt;
    private TextView usernameTxt;
    private TextView passwordTxt;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        setupView();
        setOnclickListeners();
    }

    public void setupView() {
        btn = findViewById(R.id.button_register);
        nameTxt = findViewById(R.id.name_register);
        usernameTxt = findViewById(R.id.username_register);
        passwordTxt = findViewById(R.id.password_register);
        gson = new Gson();
    }

    public void setOnclickListeners() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userObject = new User();

                if(!nameTxt.getText().toString().equals("") && !usernameTxt.getText().toString().equals("") && !passwordTxt.getText().toString().equals("")) {
                    userObject.setName(nameTxt.getText().toString());
                    userObject.setUsername(usernameTxt.getText().toString());
                    userObject.setPassword(passwordTxt.getText().toString());

                    registerUser(userObject);
                } else {
                    //TODO: Error Handling
                    System.out.println("Text = Null");
                }
            }
        });
    }

    public void registerUser(User body) {
        ServerConnection.register(body, this, this);
    }

    @Override
    public void completionHandler(String object, String url) {
        //TODO: Object Handling.
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
