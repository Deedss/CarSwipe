package com.example.vroomrr.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.Session;
import com.example.vroomrr.User;
import com.google.gson.Gson;

public class RegisterFragment extends Fragment implements ServerCallback {
    private View root;
    private Button btn;
    private TextView nameTxt;
    private TextView usernameTxt;
    private TextView passwordTxt;
    private Gson gson;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_register, container, false);

        setupView();
        setOnclickListeners();

        return root;
    }

    public void setupView() {
        btn = root.findViewById(R.id.button_register);
        nameTxt = root.findViewById(R.id.name_register);
        usernameTxt = root.findViewById(R.id.username_register);
        passwordTxt = root.findViewById(R.id.password_register);
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
        ServerConnection.register(body, this, getActivity());
    }

    @Override
    public void completionHandler(Boolean success, Object object) {
        //TODO: Object Handling.

        if(!success) {
            Toast.makeText(this.getActivity(), "Error", Toast.LENGTH_LONG).show();
        }
    }
}
