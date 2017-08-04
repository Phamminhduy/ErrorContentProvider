package com.example.phamm.demolistcontact_danhba;

import android.content.Context;
import android.view.View;
import android.widget.TabHost;

/**
 * Created by phamm on 8/3/2017.
 */

class TabFactory  implements TabHost.TabContentFactory {
    final Context context;

    public TabFactory(Context context) {
        this.context = context;
    }

    @Override
    public View createTabContent(String s) {
        View view = new View(context);
        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        return view;
    }
}
