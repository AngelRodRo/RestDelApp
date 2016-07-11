package com.example.restdeliveryapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.restdeliveryapp.Models.Dish;

import java.util.ArrayList;

public class ListResultAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Dish> listDishes;
    private ArrayList<Dish> filteredData;
    private ItemFilter mFilter = new ItemFilter();
    private LayoutInflater mInflater;

    public ListResultAdapter(Context ctx){
        mInflater = LayoutInflater.from(ctx);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void setData(ArrayList<Dish> arrayList){
        listDishes = arrayList;
        filteredData = arrayList;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            convertView = mInflater.inflate(R.layout.result,null);

            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
            holder.txtType = (TextView) convertView.findViewById(R.id.txtType);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtName.setText(filteredData.get(position).name);
        holder.txtPrice.setText(String.valueOf( filteredData.get(position).price));
        holder.txtType.setText(filteredData.get(position).type);

        return convertView;

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

    private class ViewHolder{
        TextView txtName,txtPrice,txtType;
    }

    private class ItemFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final ArrayList<Dish> list = listDishes;
            int count = list.size();
            final ArrayList<Dish> nList = new ArrayList<Dish>(count);

            String filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).name;
                if (filterableString.toLowerCase().contains(filterString)) {
                    nList.add(list.get(i));
                }
            }

            results.values = nList;
            results.count = nList.size();

            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Dish>) results.values;
            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
