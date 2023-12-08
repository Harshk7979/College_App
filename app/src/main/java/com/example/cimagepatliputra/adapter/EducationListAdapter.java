package com.example.cimagepatliputra.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cimagepatliputra.R;
import com.example.cimagepatliputra.models.EducationModel;

import java.util.List;

public class EducationListAdapter extends BaseAdapter {
    Context context;
    List<EducationModel> itemList;


    public EducationListAdapter(Context context, List<EducationModel> requestList) {
        this.context = context;
        this.itemList = requestList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public EducationModel getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {

        TextView txt_title,  txt_content;
        ImageView iv_banner;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = mInflater.inflate(R.layout.layout_education_card, null);

            holder = new ViewHolder();
            holder.txt_content = (TextView) view.findViewById(R.id.txt_content);
            holder.txt_title = (TextView) view.findViewById(R.id.txt_title);

            holder.iv_banner =(ImageView)view.findViewById(R.id.iv_icon);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }


         EducationModel obj = itemList.get(position);

        holder.txt_title.setText(obj.title);
        holder.txt_content.setText(obj.subtitle);
        holder.iv_banner.setImageResource(obj.imgId);

        return view;
    }

}



