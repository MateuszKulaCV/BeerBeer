package application.beerbeer.Menu;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;

import application.beerbeer.PubListPackage.PubListActivity;
import application.beerbeer.R;

/**
 * Created by methyll.
 */
public class MainMenu extends AppCompatActivity {
    HashMap<String,ArrayList<String>> child;
    ArrayList<String> head;
    ExpandableListAdapter adapter;
    ExpandableListView menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        PrepareList();

        menu = (ExpandableListView) findViewById(R.id.expandableMenu);
        adapter = new ExpandableListAdapter(this, head,child);
        menu.setAdapter(adapter);

        menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(groupPosition == 1)
                {
                    Intent intent = new Intent(getApplicationContext(), PubListActivity.class);
                    startActivity(intent);
                }
                return false;
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





}
