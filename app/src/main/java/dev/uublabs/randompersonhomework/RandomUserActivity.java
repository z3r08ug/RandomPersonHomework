package dev.uublabs.randompersonhomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dev.uublabs.randompersonhomework.Model.RandomResponse;
import dev.uublabs.randompersonhomework.Model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RandomUserActivity extends AppCompatActivity
{

    private TextView tvUserName;
    private TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_user);

        tvUserName = findViewById(R.id.tvUserName);
        tvLocation = findViewById(R.id.tvUserLocation);

        Result result = (Result) getIntent().getSerializableExtra("user");

        if(result != null)
        {
            tvUserName.setText(result.getName().toString());
            tvLocation.setText(result.getLocation().toString());
        }
        else

            getRandomUser();
    }

    private void getRandomUser()
    {
        RetrofitHelper.getRandomUser().enqueue(new Callback<RandomResponse>()
        {
            @Override
            public void onResponse(Call<RandomResponse> call, Response<RandomResponse> response)
            {
                Result result = response.body().getResults().get(0);

                tvUserName.setText(result.getName().toString());
                tvLocation.setText(result.getLocation().toString());
            }

            @Override
            public void onFailure(Call<RandomResponse> call, Throwable t)
            {

            }
        });
    }
}
