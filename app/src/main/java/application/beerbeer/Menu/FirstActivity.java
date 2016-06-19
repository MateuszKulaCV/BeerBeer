package application.beerbeer.Menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import application.beerbeer.BeerListPackage.BeerListFragment;
import application.beerbeer.PubListPackage.PubListFragment;
import application.beerbeer.R;
import application.beerbeer.SearchBeer.SearchBeerFragment;


/**
 * Created by methyll.
 */


public class FirstActivity extends AppCompatActivity{
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView upDrawerContent;
    Bundle data = new Bundle();
    String strResponse;
    Fragment fragment = null;
    String Title = "BeerB2eer";


    @Override
    public void onBackPressed() {
       if(getSupportFragmentManager().getBackStackEntryCount()>1)
       {
           getSupportFragmentManager().popBackStack();
       }


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsrlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(Title);
        toolbar.setTitleTextColor(Color.WHITE);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        strResponse = (String) getIntent().getExtras().getString("strResponse");
        setupDrawerContent(nvDrawer);



    }

   public void PubtoBeer(String pubposition)
    {
         fragment = new BeerListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        data.putString("pubposition", pubposition);
        data.putString("strResponse",strResponse);
        fragment.setArguments(data);
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    @Override
      public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }



        return super.onOptionsItemSelected(item);
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked

        switch(menuItem.getItemId()) {
            case R.id.All_pubs:
                fragment = new PubListFragment();
                data.putString("val",strResponse);
                data.putString("fav",null);
                fragment.setArguments(data);
                Title = "All Pubs";
                break;
            case R.id.Favourite:
                fragment = new PubListFragment();
                data.putString("val",strResponse);
                data.putString("fav", getIntent().getExtras().getString("userFav"));
                fragment.setArguments(data);
                Title = "Favourite";
                break;
            case R.id.Search:
                fragment = new SearchBeerFragment();
                data.putString("strResponse", strResponse);
                fragment.setArguments(data);
                Title = "Search";
                break;
            case R.id.Logout:
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
                break;
            default:
                fragment = new PubListFragment();
                data.putString("val", "def");
                data.putString("fav",null);
                fragment.setArguments(data);
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(null).commit();
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        getSupportActionBar().setTitle(Title);
        mDrawer.closeDrawers();
    }



}
