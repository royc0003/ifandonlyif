package com.example.a24feb1630;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity implements View.OnClickListener {

    private CardView foodCard, entertainmentCard, retailCard, othersCard, matchesCard, messagesCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        foodCard = (CardView) findViewById(R.id.food);
        entertainmentCard = (CardView) findViewById(R.id.entertainment);
        retailCard = (CardView) findViewById(R.id.retail);
        othersCard = (CardView) findViewById(R.id.others);
        matchesCard = (CardView) findViewById(R.id.homepagematches);
        messagesCard = (CardView) findViewById(R.id.homepagemessages);


        //add click listener to cards
        foodCard.setOnClickListener(this);
        entertainmentCard.setOnClickListener(this);
        retailCard.setOnClickListener(this);
        othersCard.setOnClickListener(this);
        matchesCard.setOnClickListener(this);
        messagesCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.food:
                i = new Intent(this, FoodDealsPage.class);
                startActivity(i);
                break;
            case R.id.entertainment:
                i = new Intent(this, EntertainmentDealsPage.class);
                startActivity(i);
                break;
            case R.id.retail:
                i = new Intent(this, RetailDealsPage.class);
                startActivity(i);
                break;
            case R.id.others:
                i = new Intent(this, OthersDealsPage.class);
                startActivity(i);
                break;
            case R.id.homepagematches:
                i = new Intent(this, MatchesPage.class);
                startActivity(i);
                break;
            case R.id.homepagemessages:
                i = new Intent(this, OthersDealsPage.class);
                startActivity(i);
                break;
        }
    }
}
