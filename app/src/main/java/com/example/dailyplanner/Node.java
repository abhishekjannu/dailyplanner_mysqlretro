package com.example.dailyplanner;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Node {

    @POST("/register/")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("fname") String fname,
                                    @Field("phone") String phone,
                                    @Field("email") String email,
                                    @Field("password") String password);
    @POST("/login/")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @GET("/post")
    Call<List<Post>> getPlans();

    @POST("/addschedule/")
    @FormUrlEncoded
    Observable<String> schedule(@Field("sdate") String sdate,
                                    @Field("stime") String stime,
                                    @Field("description") String description);


}
