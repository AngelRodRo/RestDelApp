package com.example.restdeliveryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.restdeliveryapp.Models.Dish;

import java.util.ArrayList;

public class ListResultAdapter extends BaseAdapter {

    ArrayList<Dish> listDishes;

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList arrayList){
        listDishes = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Dish getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
