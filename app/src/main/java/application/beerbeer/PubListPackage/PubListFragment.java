package application.beerbeer.PubListPackage;

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

import application.beerbeer.Menu.FirstActivity;
import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by methyll.
 */
public class PubListFragment extends Fragment {


    ListView listView;
    Response objResponse;
    CustomAdapter adapter;
    Gson gson;
    String strResponse;
    String fav;
    ArrayList<Response.PubsBean> favpubs;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_publist,container,false);
        setView(view);
        return view;
    }




    protected void setView(View view)
    {



        listView = (ListView) view.findViewById(R.id.list);
        /**
         * response from FirstActivity
         */
        if(strResponse==null) {
            strResponse = getArguments().getString("val");
        }

        gson = new Gson();
        objResponse = gson.fromJson(strResponse, Response.class);

        /**
         * checking if something is in fav
         */
        try
        {
            fav = getArguments().getString("fav");
        }catch (NullPointerException e)
        {

        }
        if(fav==null) {
            adapter = new CustomAdapter(getActivity().getApplicationContext(), objResponse.getPubs());

        }else
        {

            String arr[] = null;
            arr = fav.split("/");
            favpubs = new ArrayList<>();
            for(String s: arr)
            {
                Log.d("String s = ",s);
                for(Response.PubsBean temp:objResponse.getPubs())
                {
                    Log.d("Temp = ",temp.getPub());
                    if(temp.getPub().equals(s))
                    {
                        favpubs.add(temp);
                    }
                }
            }
            adapter = new CustomAdapter(getActivity().getApplicationContext(), favpubs);
        }

        listView.setAdapter(adapter);
        /**
         * setting new View depends on which pub was choose
         * sending json string
         * sending position
         */
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               ((FirstActivity) getActivity()).PubtoBeer(objResponse.getPubs().get(position).getPub());


           }
       });

        /**
         * display image of pub by longclick
         * method to improve!!!
         */
     /*   listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //dialogfragment
                Toast.makeText(getApplicationContext(), objResponse.getPubs().get(position).getLink(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });*/

    }


}
