package com.moin.graphqlapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.moin.graphqlapp.Models.UserModel;

public class UserAdapter extends PagedListAdapter<UserModel, UserAdapter.UserViewHolder> {

    private Context context;

    public UserAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = getItem(position);

        holder.user_id.setText(user.getUser_id());
        holder.name.setText(user.getName());
        holder.university.setText(user.getUniversity());
        holder.education.setText(user.getEducation());
        holder.course.setText(user.getCourse());
    }

    private static DiffUtil.ItemCallback<UserModel> diffCallback =
            new DiffUtil.ItemCallback<UserModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
                    return oldItem.getUser_id().equals(newItem.getUser_id());
                }

                @Override
                public boolean areContentsTheSame(@NonNull UserModel oldItem, @NonNull UserModel newItem) {
                    return oldItem.equals(newItem);
                }
            };

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView user_id;
        TextView name;
        TextView university;
        TextView education;
        TextView course;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            user_id = itemView.findViewById(R.id.user_id_tv);
            name = itemView.findViewById(R.id.name_tv);
            university = itemView.findViewById(R.id.university_tv);
            education = itemView.findViewById(R.id.education_tv);
            course = itemView.findViewById(R.id.course_tv);
        }
    }
}
