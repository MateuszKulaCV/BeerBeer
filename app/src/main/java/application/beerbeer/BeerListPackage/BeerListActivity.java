package application.beerbeer.BeerListPackage;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import application.beerbeer.PopUpFrag.BeerDialogFragment;
import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;


public class BeerListActivity extends AppCompatActivity{

        Gson gson;
        String strResponse,positionResponse;
        Response objresp;
        ListView listView;
        CustomAdapterBeers adapter;
        List<Response.BeersBean> listadap = new ArrayList<>();

        FragmentManager fm = getSupportFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beerlist);
    /**
    * getting intent from PubListActivity
    */

        strResponse = (String) getIntent().getExtras().getString("objres");
        positionResponse = (String) getIntent().getExtras().getString("beerPosition");

        /**
         * actionbar homebutton
         */
        getSupportActionBar().setTitle(positionResponse);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        listView = (ListView) findViewById(R.id.beerlist);
        gson = new Gson();
        objresp = gson.fromJson(strResponse, Response.class);

        /**
         * adding to listadap beer that equals to positionResponse
         */
           for(int i=0;i<objresp.getBeers().size();i++)
            {
                if(positionResponse.equals(objresp.getBeers().get(i).getPub()))
                {
                listadap.add(objresp.getBeers().get(i));
              }

            }



        adapter= new CustomAdapterBeers(BeerListActivity.this, listadap);
        listView.setAdapter(adapter);




        /**
         * onclick-> display dialogfragment with imageview, etc.
         *@param BeerName, HalfPrice, ThreePrice, Image
         */
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BeerDialogFragment beerDialogFragment = new BeerDialogFragment();
               beerDialogFragment.setBeerName(listadap.get(position).getPiwo());
               beerDialogFragment.setHalfPricestr("42");
               beerDialogFragment.setThreePricestr("22");
               beerDialogFragment.setImage(listadap.get(position).getLink());
                beerDialogFragment.show(fm, "BeerDialogFragment");

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
