package com.example.restdeliveryapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.restdeliveryapp.Models.Dish;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvDishes;
    ListResultAdapter adapter;
    ArrayList<Dish> listDishes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ListResultAdapter();
        adapter.setData(listDishes);

        lvDishes = (ListView) findViewById(R.id.lvDishes);

        lvDishes.setAdapter(adapter);

        handleIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        super.onCreateOptionsMenu(menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void handleIntent(Intent intent){
        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    public void getBestsDishes(){

    }

}
