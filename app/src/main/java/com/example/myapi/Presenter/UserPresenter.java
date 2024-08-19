package com.example.myapi.Presenter;

import android.util.Log;

import com.example.myapi.Activity.MainActivity;
import com.example.myapi.Adapter.RecyclerAdapter;
import com.example.myapi.Models.Model;
import com.example.myapi.Methods.Methods;
import com.example.myapi.Methods.Retrofitmain;
import com.example.myapi.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private static final String TAG = "UserPresenter";
    private final Methods method;
    private final UserView userView;

    public UserPresenter(UserView userView) {
        this.userView = userView;
        this.method = Retrofitmain.getRetrofitInstance().create(Methods.class);
    }

    public void loadUserData() {
        Call<Model> call = method.getAllData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArrayList<Model.data> data = response.body().getData();
                    userView.showUserData(data);
                } else {
                    userView.showError("Failed to load data");
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                userView.showError(t.getMessage());
            }
        });
    }
    public void putreq(){
//        User.User2.Data data = new User.User2.Data("12","abc@gmail.com","Ram","Kumar","");
//        Call<User.User2.Data> call = method.putinfo("12",data);
//        call.enqueue(new Callback<User.User2.Data>() {
//            @Override
//            public void onResponse(Call<User.User2.Data> call, Response<User.User2.Data> response) {
//                if(!response.isSuccessful()){
//                    return;
//                }
//                ArrayList<User.User2.Data> putlist = new ArrayList<>();
//                putlist.add(response.body());
//                RecyclerAdapter adapter = new RecyclerAdapter(putlist);
//            }
//
//            @Override
//            public void onFailure(Call<User.User2.Data> call, Throwable throwable) {
//
//            }
//        });
    }

    public void postcall(String n, String j) {
        Call<User> call = method.getUserInformation(n, j);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse: Code :" + response.code());
                    Log.e(TAG, "onResponse: Name :" + response.body().getName());
                    Log.e(TAG, "onResponse: Created At :" + response.body().getCreatedAt());
                    Log.e(TAG, "onResponse: ID :" + response.body().getId());
                    Log.e(TAG, "onResponse: Job :" + response.body().getJob());
                    userView.onPostSuccess();
                } else {
                    userView.showError("Failed to post data");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.e(TAG, "onFailure: " + throwable.getMessage());
                userView.showError("Error: " + throwable.getMessage());
            }
        });
    }

    public interface UserView {
        void showUserData(ArrayList<Model.data> data);
        void showError(String message);
        void onPostSuccess();
    }
}
