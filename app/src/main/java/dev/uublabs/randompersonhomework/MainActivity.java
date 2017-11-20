package dev.uublabs.randompersonhomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleCalls(View view)
    {
        switch (view.getId())
        {
            case R.id.btnGetRandomUser:
                startActivity(new Intent(this, RandomUserActivity.class));
                break;
            case R.id.btnGetRandomUsers:
                startActivity(new Intent(this, RandomUsersActivity.class));
                break;
            case R.id.btnGetRandomFemales:
                Intent intent = new Intent(this, RandomFemalesActivity.class);
                intent.putExtra("gender", "?gender=female");
                startActivity(intent);
                break;
            case R.id.btnGetRandomMales:
                startActivity(new Intent(this, RandomMalesActivity.class));
                break;
        }
    }
}
