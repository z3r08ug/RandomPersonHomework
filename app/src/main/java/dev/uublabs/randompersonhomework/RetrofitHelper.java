package dev.uublabs.randompersonhomework;

import dev.uublabs.randompersonhomework.Model.RandomResponse;
import dev.uublabs.randompersonhomework.Util.NetworkUtils;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;

/**
 * Created by Admin on 11/20/2017.
 */

public class RetrofitHelper
{
    public static final String BASE_URL = "https://randomuser.me/";

    public static Retrofit create()
    {
        Retrofit retrofit = NetworkUtils.configureRetrofitClient();

        return retrofit;
    }

    //call interface to get response
    public static Call<RandomResponse> getRandomUser()
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomUser();
    }

    public static Call<RandomResponse> getRandomUsers()
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomUsers();
    }

    public static Call<RandomResponse> getRandomFemales()
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomFemales();
    }
    public static Call<RandomResponse> getRandomMales()
    {
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomMales();
    }

    //create an interface for http verbs
    public interface RetrofitService
    {
        @GET("api/")
        Call<RandomResponse> getRandomUser();

        @GET("api/?results=10")
        Call<RandomResponse> getRandomUsers();

        @GET("api/?results=10&gender=female")
        Call<RandomResponse> getRandomFemales();

        @GET("api/?results=10&gender=female")
        Call<RandomResponse> getRandomMales();
    }
}