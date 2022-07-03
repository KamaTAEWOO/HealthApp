package com.health.healthapp.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.health.healthapp.R;

import java.util.List;

public class WeightAdapter extends RecyclerView.Adapter<WeightAdapter.ViewHolder> {

    private List<WeightResult> weightList;

    public WeightAdapter(List<WeightResult> list) {
        weightList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_weight, parent, false);
        WeightAdapter.ViewHolder vh = new WeightAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeightResult item = weightList.get(position);
        holder.date.setText(item.date);
        holder.pastWeight.setText(String.valueOf(item.pastWeight));
        holder.currentWeight.setText(String.valueOf(item.currentWeight));
    }

    @Override
    public int getItemCount() {
        return weightList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView pastWeight;
        TextView currentWeight;

        ViewHolder(View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.itemTitle);
            pastWeight = itemView.findViewById(R.id.itemSinger);
            currentWeight=itemView.findViewById(R.id.itemPlayTime);
        }
    }
}

