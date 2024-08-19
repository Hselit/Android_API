package com.example.myapi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.Models.Model;
import com.example.myapi.Adapter.RecyclerAdapter;
import com.example.myapi.Presenter.UserPresenter;
import com.example.myapi.Presenter.UserPresenter.UserView;
import com.example.myapi.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserView {

    private Button btn;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private UserPresenter userPresenter;
    private ArrayList<Model.data> modelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        btn = findViewById(R.id.button);
        modelArrayList = new ArrayList<>();

        recyclerAdapter = new RecyclerAdapter(modelArrayList);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userPresenter = new UserPresenter(this);

    }

    @Override
    public void showUserData(ArrayList<Model.data> data) {
        userPresenter.loadUserData();
        modelArrayList.addAll(data);
        recyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        // Handle error (e.g., show a Toast or Log the error)
    }

    @Override
    public void onPostSuccess() {

    }

    public void buttonclick(View view) {
        if (view.getId() == R.id.button) {
            userPresenter.loadUserData();
        }
    }

    public void postbtn(View view) {
        if (view.getId() == R.id.addbtn) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
    }
}
