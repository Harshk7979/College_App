package com.example.cimagepatliputra.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cimagepatliputra.R;
import com.example.cimagepatliputra.models.EducationModel;
import com.example.cimagepatliputra.models.NewsEventModel;

import java.util.List;



public class NewsEventAdapter extends BaseAdapter {
    Context context;
    List<NewsEventModel> itemList;


    public NewsEventAdapter(Context context, List<NewsEventModel> requestList) {
        this.context = context;
        this.itemList = requestList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public NewsEventModel getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {

        TextView txt_title,  txt_desc;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.news_event_layout, null);

            holder = new ViewHolder();
            holder.txt_desc = (TextView) view.findViewById(R.id.txt_desc);
            holder.txt_title = (TextView) view.findViewById(R.id.txt_title);

            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        NewsEventModel obj = itemList.get(position);

        holder.txt_title.setText(obj.title);
        holder.txt_desc.setText(obj.description);


        return view;
    }

}

