package com.example.vroomrr.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.vroomrr.Cryptography;
import com.example.vroomrr.R;
import com.example.vroomrr.ServerCallback;
import com.example.vroomrr.ServerConnection;
import com.example.vroomrr.Session;
import com.example.vroomrr.User;
import com.google.gson.Gson;

public class LoginFragment extends Fragment implements ServerCallback {
    private View root;
    private Button btn;
    private TextView usernameTxt;
    private TextView passwordTxt;
    private Gson gson;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_login, container, false);

        setupView();
        setOnclickListeners();

        return root;

    }

    public void setupView() {
        btn = root.findViewById(R.id.button_login);
        usernameTxt = root.findViewById(R.id.username_login);
        passwordTxt = root.findViewById(R.id.password_login);
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
    }

    public void loginUser(User body) {
        ServerConnection.login(body, this, getActivity());
    }

    @Override
    public void completionHandler(Boolean success, Object object) {
        Session session = gson.fromJson(object.toString(), Session.class);

        //TODO: Show error if there.
        if(!success) {
            Toast.makeText(this.getActivity(), "Error", Toast.LENGTH_LONG).show();
        }
    }
}
