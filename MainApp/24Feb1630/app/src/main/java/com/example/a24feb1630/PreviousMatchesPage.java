package com.example.a24feb1630;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class PreviousMatchesPage extends AppCompatActivity {

    ListView listView;
    ListViewAdapter adapter;
    String[] title;
    String[] description;
    int[] icon;
    ArrayList<Model> arrayList = new ArrayList<Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("TESTING");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("1Pair");


        //title of deals from webscraping is put here
        title = new String[]{"Shahrin Chua", "Royce Ang"};
        //description taken from webscraping is put here
        description = new String[]{"1-For-1 Starbucks\n 3.56 Rating", "1-For-1 USS\n 4.00 Rating"};
        //images taken from webscraping is put here, have to save images to drawables first though
        icon = new int[]{R.drawable.shahrin, R.drawable.royce};

        listView = findViewById(R.id.listView);
        /*
        for(int i = 0; i<title.length; i++){
            Model model = new Model(title[i], description[i], icon[i]);
            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        adapter = new ListViewAdapter(this, arrayList);

        //bind the adapter to the listview
        listView.setAdapter(adapter);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(TextUtils.isEmpty(s)) {
                    adapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    adapter.filter(s);
                }
                return true;
            }
        });

        return true;
    }

    //for side bar menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.menuMatches){
            //do your functionality here
            System.out.println("Menu Matches clicked");
            Intent startIntent = new Intent(getApplicationContext(), MatchesPage.class);
            startIntent.putExtra("com.example.a24feb1630.SOMETHING", "Matches Shown here"); // pass information to another activity
            startActivity(startIntent);
            return true;
        }
        if(id==R.id.menuPreviousMatches){
            //do your functionality here
            //return true;
        }
        if(id==R.id.menuPendingDeals){
            //do your functionality here
            //return true;
        }
        if(id==R.id.menuMyProfile){
            //do your functionality here
            Intent startIntent = new Intent(getApplicationContext(), MyProfile.class);
            //startIntent.putExtra("com.example.a24feb1630.SOMETHING", "Matches Shown here"); // pass information to another activity
            startActivity(startIntent);
            return true;
            //return true;
        }
        if(id==R.id.menuMessages){
            //do your functionality here
            // return true;
        }
        if(id==R.id.menuSavedDeals){
            //do your functionality here
            //return true;
        }

        //return super.onOptionsItemSelected(item);
        return false;
    }
}

/*design row of listview
 * adding menu to add searchview in actionbar
 * add model class
 * add adapter class
 * add some images in drawable folder
 * run project and test the listview and search view
 * Now handle item click to move to new activity
 * Change Actionbar title of both activity
 * add back button in actionbar of SelectedDealPage
 * handle item clicks
 * */
