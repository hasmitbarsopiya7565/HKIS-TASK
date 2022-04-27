package com.example.hkinfosoft.Users.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hkinfosoft.R;
import com.example.hkinfosoft.Rest.ApiClient;
import com.example.hkinfosoft.Rest.ApiInterface;
import com.example.hkinfosoft.Users.Adapter.UserListAdapter;
import com.example.hkinfosoft.Users.Model.UserModel;
import com.example.hkinfosoft.Util.CustomProgressDialog;
import com.example.hkinfosoft.Util.Helper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Users extends AppCompatActivity {

    private List<UserModel> usersList;
    private RecyclerView usersListRv;
    private Helper helper;
    private AppCompatButton backPageBtn,nextPageBtn;
    private int result,page;
    private TextView pageNoTv;
    private NestedScrollView nestedScrollView;
    private RelativeLayout paginationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.users));

        //bind
        bind();

        //action
        action();
    }

    private void action() {
        //show full screen
        helper.showFullScreen(Users.this);

        //custom progress dialog
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(Users.this);

        //api interface
        ApiInterface apiInterface = ApiClient.getRetrofitObject().create(ApiInterface.class);


        //user list api
        usersListApi(customProgressDialog,apiInterface,result,page);

        //next page
        nextPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page<10){
                    page++;
                    //user list api
                    usersListApi(customProgressDialog,apiInterface,result,page);
                    if(page>1 && backPageBtn.getVisibility() == View.GONE){
                        backPageBtn.setVisibility(View.VISIBLE);
                    }else if(page == 10){
                        nextPageBtn.setVisibility(View.GONE);
                    }
                }

            }
        });

        //back page
        backPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(page>1){
                    page--;
                    //user list api
                    usersListApi(customProgressDialog,apiInterface,result,page);
                    if(page==1){
                        backPageBtn.setVisibility(View.GONE);
                    }else if(page < 10 && nextPageBtn.getVisibility() == View.GONE){
                        nextPageBtn.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
    }

    private void usersListApi(CustomProgressDialog customProgressDialog, ApiInterface apiInterface, int result, int page) {
        usersList.clear();
        //show custom progress dialog
        customProgressDialog.showCustomProgressDialog();

        nestedScrollView.scrollTo(usersListRv.getScrollX(),usersListRv.getScrollY());

        //request user list api
        Call<JsonObject> callGetUserList = apiInterface.getUserList(result,page);

        callGetUserList.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                //dismiss custom progress dialog
                customProgressDialog.dismissCustomProgressDialog();

                if(response.code() == 200){

                    JsonArray jsonArray = response.body().get("results").getAsJsonArray();
                    UserModel userModel = new UserModel();
                    Gson gson = new Gson();
                    //this loop use for multiple data of json object in Json Array
                    for(int i=0;i<jsonArray.size();i++){
                        userModel = gson.fromJson(jsonArray.get(i).toString(),UserModel.class);
                        usersList.add(userModel);
                    }

                    if(usersList.size()>0){
                        UserListAdapter userListAdapter = new UserListAdapter(Users.this,usersList);
                        usersListRv.setLayoutManager(new LinearLayoutManager(Users.this,LinearLayoutManager.VERTICAL,false));
                        usersListRv.setAdapter(userListAdapter);
                        paginationLayout.setVisibility(View.VISIBLE);
                        pageNoTv.setText(String.format("%s %s %s",response.body().get("info").getAsJsonObject().get("page").getAsString(),"/","10"));
                    }


                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                customProgressDialog.dismissCustomProgressDialog();
                Log.e("Error",t.getMessage());
            }
        });
    }

    private void bind() {
        helper = new Helper();
        usersList = new ArrayList<>();
        usersListRv = findViewById(R.id.usersListRv);
        nextPageBtn = findViewById(R.id.nextPageBtn);
        backPageBtn = findViewById(R.id.backPageBtn);
        pageNoTv = findViewById(R.id.pageNoTv);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        paginationLayout = findViewById(R.id.paginationLayout);
        result = 10;
        page = 1;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}