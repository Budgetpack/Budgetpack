package com.sansi.acerbilgisayar.budgetpack.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sansi.acerbilgisayar.budgetpack.Classes.City;
import com.sansi.acerbilgisayar.budgetpack.R;

import java.util.List;

/**
 * Created by Acer Bilgisayar on 5.9.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<City> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<City> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.countryName.setText(itemList.get(position).getName());
        //holder.countryPhoto.setImageResource(itemList.get(position).getPhoto());
        holder.cityName = itemList.get(position).getName();
        switch(itemList.get(position).getName()){
            /*case "Barcelona":
                holder.countryPhoto.setImageResource(R.drawable.walks_barcelona_1);
                break;*/
            case "Belgrade":
                holder.countryPhoto.setImageResource(R.drawable.belgrade);
                break;
            case "Berlin":
                holder.countryPhoto.setImageResource(R.drawable.berlin);
                break;
            case "Brussels":
                holder.countryPhoto.setImageResource(R.drawable.brussels);
                break;
            case "Bucharest":
                holder.countryPhoto.setImageResource(R.drawable.bucharest);
                break;
            case "Budapest":
                holder.countryPhoto.setImageResource(R.drawable.budapest);
                break;
            case "Cophenagen":
                holder.countryPhoto.setImageResource(R.drawable.copenhagen);
                break;
            case "Dublin":
                holder.countryPhoto.setImageResource(R.drawable.dublin);
                break;
            case "Hamburg":
                holder.countryPhoto.setImageResource(R.drawable.hamburg);
                break;
            case "Istanbul":
                holder.countryPhoto.setImageResource(R.drawable.istanbul);
                break;
            case "Kiev":
                holder.countryPhoto.setImageResource(R.drawable.kiev);
                break;
            case "London":
                holder.countryPhoto.setImageResource(R.drawable.london);
                break;
            case "Madrid":
                holder.countryPhoto.setImageResource(R.drawable.madrid);
                break;
            case "Milan":
                holder.countryPhoto.setImageResource(R.drawable.milan);
                break;
            case "Moscow":
                holder.countryPhoto.setImageResource(R.drawable.moscow);
                break;
            case "Munich":
                holder.countryPhoto.setImageResource(R.drawable.munich);
                break;
            case "Paris":
                holder.countryPhoto.setImageResource(R.drawable.paris);
                break;
            case "Prague":
                holder.countryPhoto.setImageResource(R.drawable.prague);
                break;
            case "Rome":
                holder.countryPhoto.setImageResource(R.drawable.rome);
                break;
            case "Saint Petersburg":
                holder.countryPhoto.setImageResource(R.drawable.saintpetersburg);
                break;
            case "Sofia":
                holder.countryPhoto.setImageResource(R.drawable.sofia);
                break;
            case "Stockholm":
                holder.countryPhoto.setImageResource(R.drawable.stockholm);
                break;
            case "Vienna":
                holder.countryPhoto.setImageResource(R.drawable.vienna);
                break;
            case "Warsaw":
                holder.countryPhoto.setImageResource(R.drawable.warsaw);
                break;
            default:
                holder.countryPhoto.setImageResource(R.drawable.walks_barcelona_1);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}