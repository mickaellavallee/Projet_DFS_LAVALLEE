package com.projetdfs.lavallee.mickapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class SecondActivityAdapter extends BaseAdapter {
    List<Second> biblio;
    LayoutInflater inflater;
    Context context;
    private static final int TYPE_ITEM_PAIR = 0;
    private static final int TYPE_ITEM_IMPAIR = 1;
    private static final int TYPE_MAX_COUNT = 2;

    private class ViewHolder {
        ImageView ivImage;
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
            if (getItemViewType(position)==TYPE_ITEM_PAIR){
                convertView = inflater.inflate(R.layout.activity_second, null);
            }
            else {
                convertView = inflater.inflate(R.layout.activity_second_bis, null);
            }
            holder.ivImage = (ImageView) convertView.findViewById(R.id.img);
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
        System.out.println(second.getImage());
        holder.tvType.setText(second.getType());
        holder.tvTitre.setText(second.getTitre());
        String distance = second.getDistance().toString();
        String imageURL = second.getImage();
        Picasso.get().load(imageURL).into(holder.ivImage);
        holder.tvDistance.setText(distance);
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

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            return TYPE_ITEM_PAIR;
        }
        else return TYPE_ITEM_IMPAIR;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }
}