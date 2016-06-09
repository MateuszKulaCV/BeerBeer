package application.beerbeer.PubListPackage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import application.beerbeer.BeerListPackage.BeerListActivity;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;


public class PubListActivity extends AppCompatActivity {

    ListView listView;
    Response objResponse;
    CustomAdapter adapter;
    Gson gson;
    String strResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publist);
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

        getSupportActionBar().setTitle("Pub's List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (ListView) findViewById(R.id.list);
        /**
         * intent response from MainMenu1
         */
        strResponse = (String) getIntent().getExtras().getString("strResponse");

        gson = new Gson();
        objResponse = gson.fromJson(strResponse,Response.class);
        adapter = new CustomAdapter(PubListActivity.this, objResponse.getPubs());
        listView.setAdapter(adapter);




        /**
         * setting new View depends on which pub was choose
         * sending json string
         * sending position
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), BeerListActivity.class);
                intent.putExtra("strResponse", strResponse);
                intent.putExtra("pubposition", objResponse.getPubs().get(position).getPub());
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
