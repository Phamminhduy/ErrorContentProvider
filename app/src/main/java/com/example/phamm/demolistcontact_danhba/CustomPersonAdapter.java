package com.example.phamm.demolistcontact_danhba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by phamm on 8/3/2017.
 */

public class CustomPersonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Person> list;

    public CustomPersonAdapter(Context context, int layout, ArrayList<Person> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public ArrayList<Person> getList() {
        return list;
    }

    public void setList(ArrayList<Person> list) {
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public  long getItemId(int i) {
        return i;
    }

    @Override
    public View getView( int i, View view, ViewGroup viewGroup) {
        viewHolder viewholder;
        if(view ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            viewholder = new viewHolder();
            viewholder.txtName = (TextView) view.findViewById(R.id.textView);
            viewholder.txtNumber = (TextView) view.findViewById(R.id.text2);
            view.setTag(viewholder);
         }else{
            viewholder = (viewHolder) view.getTag();
        }
        Person person = list.get(i);
        viewholder.txtName.setText(person.getName());
        viewholder.txtNumber.setText(person.getNumber());

        return view;
    }
    public class viewHolder{
        TextView txtName;
        TextView txtNumber;

    }
}
