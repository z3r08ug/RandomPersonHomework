package dev.uublabs.randompersonhomework;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dev.uublabs.randompersonhomework.Model.RandomResponse;
import dev.uublabs.randompersonhomework.Model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomUsersActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private List<Result> randomUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_users);

        randomUsers = new ArrayList<>();

        recyclerView = findViewById(R.id.rvRandomUsers);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(itemAnimator);

        final Handler handler = new Handler(new Handler.Callback()
        {
            @Override
            public boolean handleMessage(Message msg)
            {
                RandomResponse user = (RandomResponse) msg.getData().getSerializable("users");
                randomUsers = user.getResults();

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(randomUsers);

                recyclerView.setAdapter(recyclerAdapter);
                return true;
            }
        });

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                RetrofitHelper.getRandomUsers().enqueue(new Callback<RandomResponse>()
                {
                    @Override
                    public void onResponse(Call<RandomResponse> call, Response<RandomResponse> response)
                    {
                        RandomResponse user = response.body();
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("users", user);
                        message.setData(bundle);
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onFailure(Call<RandomResponse> call, Throwable t)
                    {
                        Log.d("RANDOM_BULL", "onResponse: "+t);

                    }
                });
            }
        }).start();
    }
}
