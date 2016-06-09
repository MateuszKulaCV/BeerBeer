package application.beerbeer.SearchBeer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;


import com.loopj.android.http.AsyncHttpResponseHandler;

import application.beerbeer.R;
import application.beerbeer.ResponsePack.GetResponseAPI;
import cz.msebera.android.httpclient.Header;

/**
 * Created by methyll.
 */
public class SearchBeerActivity extends AppCompatActivity implements Filterable{

    SearchView searchView;
    TextView txt;
    final String URL= "dbcon.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbeer);
        getSupportActionBar().setTitle("Pub's List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        searchView = (SearchView) findViewById(R.id.searchView);
       // txt = (TextView) findViewById(R.id.txt);

        searchviewMethod();




    }


    void searchviewMethod()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txt.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txt.setText(newText);
                return false;
            }
        });

    }

    @Override
    public Filter getFilter() {


        return null;
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

