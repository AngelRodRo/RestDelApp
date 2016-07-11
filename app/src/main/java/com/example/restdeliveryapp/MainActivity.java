package com.example.restdeliveryapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.restdeliveryapp.Models.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.db.Database;
import im.delight.android.ddp.db.Document;

public class MainActivity extends AppCompatActivity implements MeteorCallback{

    ListView lvDishes;
    ListResultAdapter adapter;
    ArrayList<Dish> listDishes;

    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    int ICONS[] = {R.drawable.ic_search,R.drawable.ic_search,R.drawable.ic_search,R.drawable.ic_search,R.drawable.ic_search};
    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.ic_search;
    private Toolbar toolbar;

    RecyclerView mRecyclerView; // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;

    ActionBarDrawerToggle mDrawerToggle;
    Database database;
    Meteor mMeteor;
    Document[] dishes;
    Document user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ListResultAdapter(this);
        listDishes = new ArrayList<>();

        mMeteor = MeteorSingleton.getInstance();

        database = mMeteor.getDatabase();

        getBestsDishes();

        lvDishes = (ListView) findViewById(R.id.lvDishes);

        adapter.setData(listDishes);

        lvDishes.setAdapter(adapter);

        lvDishes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Dish dish = adapter.getItem(position);
//                Intent i = new Intent(MainActivity.this,ProfileOrderActivity.class);
//                i.putExtra("name",dish.name);
//                i.putExtra("type",dish.type);
//                i.putExtra("price",dish.price);
//                startActivity(i);

            }
        });

        handleIntent(getIntent());
        toolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);// Letting the system know that the list objects are of fixed size

        mAdapter = new MenuNavigationAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);

        mRecyclerView.setAdapter(mAdapter); // Setting the adapter to RecyclerView
        mLayoutManager = new LinearLayoutManager(this); // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.app_name,R.string.search_hint){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

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
        user = database.getCollection("users").getDocument(mMeteor.getUserId());

        HashMap<String,String> profile = (LinkedHashMap) user.getField("profile");

        dishes = database.getCollection("dishes").whereEqual("restaurantId",profile.get("restaurantId")).find();

        for (int i = 0; i < dishes.length; i++) {
            Document dishD = dishes[i];

            Dish dish = new Dish();

            dish.name = dishD.getField("name").toString();
            dish.price = Float.parseFloat(dishD.getField("price").toString());
            dish.type = dishD.getField("type").toString();

            listDishes.add(dish);
        }

    }

    @Override
    public void onConnect(boolean signedInAutomatically) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onDataAdded(String collectionName, String documentID, String newValuesJson) {

    }

    @Override
    public void onDataChanged(String collectionName, String documentID, String updatedValuesJson, String removedValuesJson) {

    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDataRemoved(String collectionName, String documentID) {

    }
}
