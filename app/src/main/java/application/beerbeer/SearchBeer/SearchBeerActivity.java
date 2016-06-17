package application.beerbeer.SearchBeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.List;

import application.beerbeer.BeerListPackage.BeerListActivity;
import application.beerbeer.PubListPackage.CustomAdapter;
import application.beerbeer.R;

import application.beerbeer.ResponsePack.Response;


/**
 * Created by methyll.
 */
public class SearchBeerActivity extends AppCompatActivity{

    SearchView searchView;
    final String URL= "dbcon.php";
    String strResponse;
    Response objResponse;
    Gson gson;
    CustomAdapter adapter;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbeer);
        getSupportActionBar().setTitle("Pub's List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        strResponse = getIntent().getExtras().getString("strResponse");

        searchView = (SearchView) findViewById(R.id.searchView);
        listView= (ListView) findViewById(R.id.listView);


        gson = new Gson();
        objResponse = gson.fromJson(strResponse, Response.class);
        adapter = new CustomAdapter(SearchBeerActivity.this, objResponse.getPubs());
        adapter.setObjResponse(objResponse);
        listView.setAdapter(adapter);
        searchviewMethod();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BeerListActivity.class);
                intent.putExtra("strResponse", strResponse);
                Response.PubsBean item = (Response.PubsBean) listView.getItemAtPosition(position);
                intent.putExtra("pubposition",item.getPub());
                startActivity(intent);
            }
        });


    }


    void searchviewMethod()
    {


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                adapter.setPubs((List<Response.PubsBean>) adapter.getpubmem());
                return false;
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

