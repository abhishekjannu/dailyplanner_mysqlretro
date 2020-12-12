package com.example.dailyplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.dailyplanner.RetrofitClient.retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
private ListView listView;
List<Post> ps;
Button addschedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        addschedule=findViewById(R.id.addschedule);
        addschedule.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i =new Intent(MainActivity.this,Schedule.class);
        startActivity(i);
    }
});
        Retrofit retrofit = RetrofitClient.getApiClient();
        Node node = retrofit.create(Node.class);
        Call<List<Post>> call = node.getPlans();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if ((!response.isSuccessful())){
                    textView.setText("Code:"+response.code());
                    return;
                }

               ps = response.body();
               listadapter li=new listadapter();
               listView.setAdapter(li);

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }

    public class listadapter extends BaseAdapter{

        @Override
        public int getCount() {
            return ps.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v=getLayoutInflater().from(MainActivity.this).inflate(R.layout.listitems,viewGroup,false);
            TextView sdate=v.findViewById(R.id.sdate);
            TextView sdesc=v.findViewById(R.id.sdesc);
            TextView stime=v.findViewById(R.id.stime);
            sdate.setText(ps.get(i).getSdate());
            sdesc.setText(ps.get(i).getDescription());
            stime.setText(ps.get(i).getStime());
            return v;
        }
    }
}