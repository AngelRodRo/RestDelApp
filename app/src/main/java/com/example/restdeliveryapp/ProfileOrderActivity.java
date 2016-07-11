package com.example.restdeliveryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import im.delight.android.ddp.Meteor;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.db.Database;
import im.delight.android.ddp.db.Document;

public class ProfileOrderActivity extends AppCompatActivity{

    Meteor mMeteor;
    Database database;
    TextView txtName,txtPrice,txtType;
    Button btnMakeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_order);

        mMeteor = MeteorSingleton.getInstance();
        database = mMeteor.getDatabase();

        txtName = (TextView) findViewById(R.id.txtName);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtType = (TextView) findViewById(R.id.txtType);

        txtName.setText(getIntent().getExtras().getString("name"));
        txtType.setText(getIntent().getExtras().getString("type"));
        txtPrice.setText(getIntent().getExtras().getString("price"));

        btnMakeOrder = (Button) findViewById(R.id.btnMakeOrder);

        btnMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
