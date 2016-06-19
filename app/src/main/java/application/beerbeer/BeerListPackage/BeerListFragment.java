package application.beerbeer.BeerListPackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import application.beerbeer.PopUpFrag.BeerDialogFragment;
import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by methyll.
 */
public class BeerListFragment extends Fragment {

    Gson gson;
    String strResponse,positionResponse;
    Response objresp;
    ListView listView;
    CustomAdapterBeers adapter;
    List<Response.BeersBean> listadap = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beerlist,container,false);
        listView = (ListView) view.findViewById(R.id.beerlist);
        Log.d("beerfrag","view created");


        SetView(view);
       return view;
    }

    public void SetView(View view)
    {
        strResponse = getArguments().getString("strResponse");
        positionResponse = getArguments().getString("pubposition");
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



        adapter= new CustomAdapterBeers(getActivity().getApplicationContext(), listadap);
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
                beerDialogFragment.setHalfPricestr(listadap.get(position).getHalfprice());
                beerDialogFragment.setThreePricestr(listadap.get(position).getThreeprice());
                beerDialogFragment.setImage(listadap.get(position).getLink());
                beerDialogFragment.show(getFragmentManager(), "BeerDialogFragment");

            }
        });
    }


}

