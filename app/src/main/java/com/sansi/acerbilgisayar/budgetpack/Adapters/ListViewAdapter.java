package com.sansi.acerbilgisayar.budgetpack.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Acer Bilgisayar on 20.9.2017.
 */

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<City> cityNamesList = null;
    private ArrayList<City> arraylist;

    public ListViewAdapter(Context context, List<City> cityNameList) {
        mContext = context;
        this.cityNamesList = cityNameList;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<City>();
        this.arraylist.addAll(cityNameList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return cityNamesList.size();
    }

    @Override
    public City getItem(int position) {
        return cityNamesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_items, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(cityNamesList.get(position).getName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        cityNamesList.clear();
        if (charText.length() == 0) {
            cityNamesList.addAll(arraylist);
        } else {
            for (City wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    cityNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
