package application.beerbeer.Menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;

import application.beerbeer.PubListPackage.PubListActivity;
import application.beerbeer.R;
import application.beerbeer.ResponsePack.GetResponseAPI;
import application.beerbeer.ResponsePack.Response;
import application.beerbeer.SearchBeer.SearchBeerActivity;
import cz.msebera.android.httpclient.Header;

/**
 * Created by methyll.
 */
public class MainMenu extends AppCompatActivity {
    HashMap<String,ArrayList<String>> child;
    ArrayList<String> head;
    ExpandableListAdapter adapter;
    ExpandableListView mainmenu;


    final public String URL = "dbcon.php";
    Gson gson;
    Response objResponse;
   public final String fav = "Marynka/Kontynuacja";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        SetConn();

        mainmenu = (ExpandableListView) findViewById(R.id.expandableMenu);



    }





    void SetConn()
    {
        GetResponseAPI.get(URL, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    String strResponse = new String(responseBody);
                setExpandableList(strResponse);

                gson = new Gson();
                objResponse = gson.fromJson(strResponse, Response.class);



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    void PrepareList()
    {


        head = new ArrayList<>();
        child = new HashMap<>();
        head.add("Fav Pubs");
        head.add("All Pubs");
        head.add("Beer in Pubs");
        ArrayList<String> favpubs = new ArrayList<>();
        favpubs.add("Marynka");
        favpubs.add("Kontynuacja");
        ArrayList<String> test = new ArrayList<>();
        child.put(head.get(0), favpubs);
        child.put(head.get(1), test);
        child.put(head.get(2), test);

    }

    void setExpandableList(final String strResponse)
    {
        PrepareList();

        adapter = new ExpandableListAdapter(this, head,child);
        mainmenu.setAdapter(adapter);

        mainmenu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (groupPosition == 1) { //All Pubs
                    Intent intent = new Intent(getApplicationContext(), PubListActivity.class);
                    intent.putExtra("strResponse",strResponse);
                    startActivity(intent);


                }else if(groupPosition == 2) //Beer in Pubs
                {
                    Intent intent = new Intent(getApplicationContext(), SearchBeerActivity.class);
                    intent.putExtra("strResponse",strResponse);
                    startActivity(intent);

                }

                return false;
            }
        });
    }



}
