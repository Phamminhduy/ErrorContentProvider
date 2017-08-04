package com.example.phamm.demolistcontact_danhba;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by phamm on 8/3/2017.
 */

public class ListContacts extends ListFragment {
    ArrayList<Person> arr;
    CustomPersonAdapter adapter;
    ListView listView;
    ImageButton imgbtn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.listcontacts,container,false);
        AnhXa();
        listView = (ListView) view.findViewById(android.R.id.list);
        getContact();
        adapter = new CustomPersonAdapter(getActivity(),R.layout.custom_item_listview,arr);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                int del = getActivity().getContentResolver().delete(PersonProvider.CONTENT_URI,PersonProvider._ID+" =?",new String[]{"name"});
                Toast.makeText(getActivity(), "Xóa Thành Công"+del, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        return view;
    }
    private void AnhXa() {

        arr = new ArrayList<>();

    }
    private void getContact(){
        String URL = "content://com.example.provider.NumberPhone/numbersphone";
        Uri numberphone = Uri.parse(URL);
        Cursor cs = getActivity().getContentResolver().query(numberphone,null,null,null,null);
        while (cs.moveToNext()){

            String name = cs.getString(cs.getColumnIndex(PersonProvider.NAME));
            String number = cs.getString(cs.getColumnIndex(PersonProvider.PHONENUMBER));
            arr.add(new Person(name,number));
        }
        cs.close();

    }

}
