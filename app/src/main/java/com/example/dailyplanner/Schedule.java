package com.example.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Schedule extends AppCompatActivity {
    EditText sdate,stime, sdescription;
    Button schedule;
    Node loginapi;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        sdate = findViewById(R.id.sdate);
        stime = findViewById(R.id.stime);
        sdescription = findViewById(R.id.sdesc);
        schedule = findViewById(R.id.Schedule);

        Retrofit retrofit = RetrofitClient.getApiClient();
        loginapi = retrofit.create(Node.class);

        schedule.setOnClickListener(new View.OnClickListener() {
            /**
             *
             * @param v
             */
            @Override
            public void onClick(View v) {
                schedule(sdate.getText().toString(),stime.getText().toString(),sdescription.getText().toString());
            }
        });

    }

    /**
     *
     *
     * calls register api with nodejs and creates a new login when a new email id entered
     */
    private void schedule(String sdatepass, String stimepass,String sdescpass) {

        Log.v("rathan","inouts"+sdatepass+stimepass);
        compositeDisposable.add(loginapi.schedule(sdatepass,stimepass,sdescpass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.v("api text","rathan"+s);
                        if (s.contains("Schedule Added Successfully")){
                            Toast.makeText(Schedule.this,"Schedule Added",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Schedule.this, MainActivity.class);
                            startActivity(intent);}
                        else
                            Toast.makeText(Schedule.this,"Error",Toast.LENGTH_LONG).show();
                    }
                }));
    }

    }
