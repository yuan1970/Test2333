package com.example.test233;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author anyu
 * @date 2022/10/16-14:47
 * @desc
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter {

    private String[] groups;
    private String[][] childs;

    private int mGroupPosition = -1,mChildPosition = -1;

    Context mcotext;
    public MyExpandableAdapter(Context mcotext,String[] groups,String[][] childs) {
        this.mcotext=mcotext;
        this.groups=groups;
        this.childs=childs;
    }

    public void setPosition(int mGroupPosition,int mChildPosition){
        this.mGroupPosition = mGroupPosition;
        this.mChildPosition = mChildPosition;
        notifyDataSetChanged();
    }


    @Override
    public int getGroupCount () {
        return groups.length;
    }

    @Override
    public int getChildrenCount (int groupPosition) {
        return childs[groupPosition].length;
    }

    @Override
    public Object getGroup (int groupPosition) {
        return groups[groupPosition];
    }

    @Override
    public Object getChild (int groupPosition, int childPosition) {
        return childs[groupPosition][childPosition];
    }

    @Override
    public long getGroupId (int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId (int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds () {
        return false;
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public View getGroupView (int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mcotext).inflate(R.layout.group_layout,null);
            holder=new ViewHolder ();
            holder.tv=(TextView) convertView.findViewById(R.id.tv);
            holder.img=(ImageView) convertView.findViewById(R.id.img_arrow);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        if(mGroupPosition == groupPosition ) {
            holder.tv.setTypeface (null,Typeface.BOLD);
            holder.tv.setTextColor (Color.parseColor ("#FF000000"));
        }else {
            holder.tv.setTypeface (Typeface.DEFAULT);
            holder.tv.setTextColor (Color.parseColor ("#FF545454"));
        }
        if (isExpanded) {
            holder.img.setImageDrawable(mcotext.getResources().getDrawable(R.drawable.ic_arrow_down));
        } else {
            holder.img.setImageDrawable(mcotext.getResources().getDrawable(R.drawable.ic_arrow_right));
        }

        holder.tv.setText(groups[groupPosition]);
        return convertView;

    }

    @Override
    public View getChildView (int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mcotext).inflate(R.layout.child_layout,null);
            holder=new ViewHolder ();
            holder.tv=(TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        if(mGroupPosition == groupPosition && mChildPosition == childPosition) {
            holder.tv.setTypeface (null,Typeface.BOLD);
            holder.tv.setTextColor (Color.parseColor ("#FF000000"));
        }else {
            holder.tv.setTypeface (Typeface.DEFAULT);
            holder.tv.setTextColor (Color.parseColor ("#FF545454"));
        }

        holder.tv.setText(childs[groupPosition][childPosition]);
        return convertView;

    }

    @Override
    public boolean isChildSelectable (int groupPosition, int childPosition) {
        return true;
    }

    ViewHolder holder;
    class ViewHolder{
        TextView tv;
        ImageView img;
    }
}
