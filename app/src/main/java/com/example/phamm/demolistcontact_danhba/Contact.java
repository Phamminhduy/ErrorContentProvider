package com.example.phamm.demolistcontact_danhba;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by phamm on 8/3/2017.
 */

public class Contact extends Fragment {
    Button btnadd;
    EditText txtname;
    EditText txtnumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact,container,false);
        btnadd = (Button) view.findViewById(R.id.ButtonAdd);
        txtname = (EditText) view.findViewById(R.id.editName);
        txtnumber = (EditText) view.findViewById(R.id.editNumber);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(PersonProvider.NAME, txtname.getText().toString());
                values.put(PersonProvider.PHONENUMBER, txtnumber.getText().toString());

                Uri uri = getActivity().getContentResolver().insert(PersonProvider.CONTENT_URI, values);
                Toast.makeText(getActivity(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

}
