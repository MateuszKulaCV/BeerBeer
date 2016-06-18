package application.beerbeer.SearchBeer;


import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import application.beerbeer.Menu.FirstActivity;
import application.beerbeer.PubListPackage.CustomAdapter;
import application.beerbeer.R;
import application.beerbeer.ResponsePack.Response;

/**
 * Created by methyll.
 */
public class SearchBeerFragment extends Fragment {
    SearchView searchView;
    final String URL= "dbcon.php";
    String strResponse;
    Response objResponse;
    Gson gson;
    CustomAdapter adapter;
    ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;
    HashSet<String> hash;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_searchbeer,container,false);
        strResponse = getArguments().getString("strResponse");

        searchView = (SearchView) view.findViewById(R.id.searchView);
        listView= (ListView) view.findViewById(R.id.listView);


        gson = new Gson();
        objResponse = gson.fromJson(strResponse, Response.class);


        hash = new HashSet<>();
        for(Response.BeersBean beers:objResponse.getBeers())
        {
            hash.add(beers.getPiwo());
        }



        adapter = new CustomAdapter(getActivity().getApplicationContext(), objResponse.getPubs());
        adapter.setObjResponse(objResponse);
        listView.setAdapter(adapter);

        searchviewMethod();
        final String[] from = new String[] {"beername"};
        final int[] to = new int[] {android.R.id.text1};
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,null,from,to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        searchView.setSuggestionsAdapter(simpleCursorAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Response.PubsBean item = (Response.PubsBean) listView.getItemAtPosition(position);
               ((FirstActivity) getActivity()).PubtoBeer(item.getPub());
            }
        });


        return view;
    }

    private void populateAdapter(String query) {
        final MatrixCursor c = new MatrixCursor(new String[]{ BaseColumns._ID, "beername" });
        Iterator<String> it = hash.iterator();
        int i=0;
        while(it.hasNext())
        {
            String temp = it.next();
            if (temp.toLowerCase().startsWith(query.toLowerCase()))
                c.addRow(new Object[] {i, temp});
            i++;
        }
        simpleCursorAdapter.changeCursor(c);
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
                populateAdapter(newText);
                return false;
            }
        });


        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionClick(int position) {
                String sugg = getSuggestion(position);
                searchView.setQuery(sugg, true);

                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position) {

                return true;
            }
        });


    }


    String getSuggestion(int position)
    {
        Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
        String Suggest = cursor.getString(cursor.getColumnIndex("beername"));
        return Suggest;
    }




}


