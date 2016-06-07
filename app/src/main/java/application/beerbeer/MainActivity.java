package application.beerbeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import application.beerbeer.BeerListPackage.BeerList;
import application.beerbeer.PubListPackage.CustomAdapter;
import application.beerbeer.ResponsePack.Response;


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
        setView();


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setView();
    }

    /**
     * setting ListView, getting data from server by asynchhtpclient
     * convert raw json to gson object(Response)
     * setting row by CustomAdapter
     *
     * */
    protected void setView()
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

        /**
         * setting new View depends on which pub was choosen
         * sending json string
         * sending position
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "yo"+position, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), BeerList.class);
                intent.putExtra("objres", gson.toJson(objResponse));
                intent.putExtra("beerPosition", objResponse.getPubs().get(position).getPub());
                startActivity(intent);


            }
        });

        /**
         * display image of pub by longclick
         * method to improve!!!
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), objResponse.getPubs().get(position).getLink(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

    }



}
