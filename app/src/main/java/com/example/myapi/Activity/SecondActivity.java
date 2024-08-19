package com.example.myapi.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapi.Models.Model;
import com.example.myapi.Presenter.UserPresenter;
import com.example.myapi.R;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements UserPresenter.UserView {

    private String n, j;
    private Button btn1;
    private EditText ed1, ed2;
    private UserPresenter userPresenter;

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ed1 = findViewById(R.id.editTextText);
        ed2 = findViewById(R.id.editTextText2);
        btn1 = findViewById(R.id.button21);
        userPresenter = new UserPresenter(this);
    }

    public void postrequestbtn(View view) {
        if (view.getId() == R.id.button21) {
            n = ed1.getText().toString();
            j = ed2.getText().toString();
            userPresenter.postcall(n, j);
        }
    }

    @Override
    public void showUserData(ArrayList<Model.data> data) {}

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostSuccess() {
        Toast.makeText(SecondActivity.this, "Details Added..", Toast.LENGTH_SHORT).show();
        ed1.setText("");
        ed2.setText("");
    }
}
