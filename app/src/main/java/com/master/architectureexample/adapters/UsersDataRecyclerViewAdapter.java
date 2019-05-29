package com.master.architectureexample.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.master.architectureexample.R;
import com.master.architectureexample.localdb.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersDataRecyclerViewAdapter extends RecyclerView.Adapter<UsersDataRecyclerViewAdapter.ViewHolder> {


    private List<User> userList = new ArrayList<>();


    public void setUsersData(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersDataRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_row_layout, parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersDataRecyclerViewAdapter.ViewHolder holder, int position) {

        holder.mNameTextView.setText(String.valueOf("Name: " + userList.get(position).getName()));
        holder.mAgeTextView.setText(String.valueOf("Age: " + userList.get(position).getAge()));
        holder.mEmailTextView.setText(String.valueOf("Email: " + userList.get(position).getEmail()));

    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mAgeTextView;
        TextView mEmailTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.nameTv);
            mAgeTextView = itemView.findViewById(R.id.ageTv);
            mEmailTextView = itemView.findViewById(R.id.emailTv);
        }
    }
}
