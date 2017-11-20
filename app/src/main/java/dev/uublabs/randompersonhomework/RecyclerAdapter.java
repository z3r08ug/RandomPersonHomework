package dev.uublabs.randompersonhomework;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dev.uublabs.randompersonhomework.Model.Result;

/**
 * Created by Admin on 11/20/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    List<Result> userList = new ArrayList<>();

    static String TAG = "RecyclerAdapter";
    public RecyclerAdapter(List<Result> list)
    {
        userList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Result randomUser = userList.get(position);
        if(randomUser != null)
        {
            holder.tvUserName.setText(randomUser.getName().toString());
        }
    }

    @Override
    public int getItemCount()
    {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView tvUserName;

        public ViewHolder(final View itemView)
        {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUsersName);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Result result = userList.get(getAdapterPosition());
                    Intent intent = new Intent(v.getContext(), RandomUserActivity.class);
                    intent.putExtra("user", result);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}