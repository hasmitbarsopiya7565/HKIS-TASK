package com.example.hkinfosoft.Users.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hkinfosoft.Users.Model.UserModel;
import com.example.hkinfosoft.Location.Activity.Location;
import com.example.hkinfosoft.R;
import com.example.hkinfosoft.Util.Helper;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListHolder> {

    Context context;
    List<UserModel> userList;
    Helper helper;

    public UserListAdapter(Context context, List<UserModel> userList) {
        this.context = context;
        this.userList = userList;
        helper = new Helper();
    }

    @NonNull
    @Override
    public UserListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_layout,parent,false);
        return new UserListHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserListHolder holder, int position) {
        UserModel um = userList.get(position);
        Picasso.with(context).load(um.getPicture().get("thumbnail").getAsString()).placeholder(R.drawable.ic_profile).into(holder.userProfileIv);
        holder.userNameTv.setText(String.format("%s %s %s %s",context.getString(R.string.name),um.getName().get("title").getAsString()+".",um.getName().get("first").getAsString(),um.getName().get("last").getAsString()));
        holder.userDobTv.setText(String.format("%s %s",context.getString(R.string.dob),helper.getDate(um.getDob().get("date").getAsString())));
        holder.userAddressTv.setText(String.format("%s %s %s %s %s %s %s",context.getString(R.string.address),um.getLocation().get("street").getAsJsonObject().get("number").getAsString()+",",um.getLocation().get("street").getAsJsonObject().get("name").getAsString()+",",um.getLocation().get("city").getAsString()+",",um.getLocation().get("state").getAsString()+",",um.getLocation().get("country").getAsString()+",",um.getLocation().get("postcode").getAsString()));
        holder.userListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Location.class);
                i.putExtra("lat",um.getLocation().get("coordinates").getAsJsonObject().get("latitude").getAsString());
                i.putExtra("lon",um.getLocation().get("coordinates").getAsJsonObject().get("longitude").getAsString());
                i.putExtra("address",holder.userAddressTv.getText().toString());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserListHolder extends RecyclerView.ViewHolder {
        TextView userAddressTv,userDobTv,userNameTv;
        CircleImageView userProfileIv;
        RelativeLayout userListLayout;
        public UserListHolder(@NonNull View itemView) {
            super(itemView);
            userAddressTv = itemView.findViewById(R.id.userAddressTv);
            userDobTv = itemView.findViewById(R.id.userDobTv);
            userNameTv = itemView.findViewById(R.id.userNameTv);
            userProfileIv = itemView.findViewById(R.id.userProfileIv);
            userListLayout = itemView.findViewById(R.id.userListLayout);
        }
    }
}
