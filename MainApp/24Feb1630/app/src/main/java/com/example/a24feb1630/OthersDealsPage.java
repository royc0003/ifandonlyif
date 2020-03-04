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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OthersDealsPage extends AppCompatActivity {

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

        listView = findViewById(R.id.listView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://128.199.167.80:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BackEndController backEndController = retrofit.create(BackEndController.class);
        Call<ArrayList<Deal>> call = backEndController.getOthersDeals();
        call.enqueue(new Callback<ArrayList<Deal>>() {
            @Override
            public void onResponse(Call<ArrayList<Deal>> call, Response<ArrayList<Deal>> response) {
                if(!response.isSuccessful()){
                    System.out.println("Oops something went wrong!");
                    return;
                }
                ArrayList<Deal> deals = response.body();
                for(Deal deal: deals){
                    //deal.printDeal();
                    Model model = new Model(deal.getName(), "", deal.getImage());
                    arrayList.add(model);
                }
                adapter = new ListViewAdapter(getApplicationContext(), arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Deal>> call, Throwable t) {
                System.out.println("Oops something went wrong!");
            }
        });
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
            System.out.println("Menu Previous Matches clicked");
            Intent startIntent = new Intent(getApplicationContext(), PreviousMatchesPage.class);
            startIntent.putExtra("com.example.a24feb1630.SOMETHING", "Matches Shown here"); // pass information to another activity
            startActivity(startIntent);
            return true;
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
