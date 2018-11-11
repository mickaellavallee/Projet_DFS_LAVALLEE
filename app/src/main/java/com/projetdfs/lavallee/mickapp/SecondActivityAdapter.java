package com.projetdfs.lavallee.mickapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SecondActivityAdapter extends BaseAdapter {

    List<Second> biblio;
    // LayoutInflater aura pour mission de charger notre fichier XML
    LayoutInflater inflater;

    private class ViewHolder {
        TextView tvType;
        TextView tvTitre;
        TextView tvDistance;
    }

    public SecondActivityAdapter(Context context, List<Second> objects) {
        inflater = LayoutInflater.from(context);
        this.biblio = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            Log.v("test", "convertView is null");
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_second, null);
            holder.tvType = (TextView) convertView.findViewById(R.id.type);
            holder.tvTitre = (TextView) convertView.findViewById(R.id.title);
            holder.tvDistance = (TextView) convertView.findViewById(R.id.distance);
            convertView.setTag(holder);
        }
        else {
            Log.v("test", "convertView is not null");
            holder = (ViewHolder) convertView.getTag();
        }

        Second second = biblio.get(position);
        holder.tvType.setText(second.getType());
        holder.tvTitre.setText(second.getTitre());
        String dist=second.getDistance().toString();
        holder.tvDistance.setText(dist);
        return convertView;
    }

    @Override
    public int getCount() {
        return biblio.size();
    }

    @Override
    public Second getItem(int position) {
        return biblio.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}