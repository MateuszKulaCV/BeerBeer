package application.beerbeer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by KOMPUTOR on 2016-05-16.
 */
public class BeerList extends AppCompatActivity{

        Gson gson;
        String strResponse,positionResponse;
        Response objresp;
        ListView listView;
        CustomAdapterBeers adapter;
        List<Response.BeersBean> listadap = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerlist);
        strResponse = (String) getIntent().getExtras().getString("objres");
        positionResponse = (String) getIntent().getExtras().getString("beerPosition");

        listView = (ListView) findViewById(R.id.beerlist);

        gson = new Gson();
        objresp = gson.fromJson(strResponse, Response.class);


           for(int i=0;i<objresp.getBeers().size();i++)
            {
                if(positionResponse.equals(objresp.getBeers().get(i).getPub()))
                {
                listadap.add(objresp.getBeers().get(i));
              }

            }
      //  Picasso.with(getApplicationContext()).load(objresp.getBeers())
        adapter= new CustomAdapterBeers(BeerList.this, listadap);
        listView.setAdapter(adapter);
    }

}
