package application.beerbeer;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    Response objResponse;
    CustomAdapter adapter;
    AsyncHttpClient client;
    String url = "http://beerparse.esy.es/dbcon.php";
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        func();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        func();
    }

    protected void func()
    {
        listView = (ListView) findViewById(R.id.list);
        client = new AsyncHttpClient();

                client.get(MainActivity.this, url, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes) {
                        String strResponse = new String(bytes);
                        gson = new Gson();
                        objResponse = gson.fromJson(strResponse, Response.class);
                        adapter = new CustomAdapter(MainActivity.this, objResponse.getPubs());
                listView.setAdapter(adapter);


            }

            @Override
            public void onFailure(int i, cz.msebera.android.httpclient.Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "yo"+position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), BeerList.class);
                intent.putExtra("objres",gson.toJson(objResponse));
                intent.putExtra("beerPosition", objResponse.getPubs().get(position).getPub());
                startActivity(intent);

            }
        });
    }

}
