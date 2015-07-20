package io.pivotal.labsboot.stores;

import android.content.Context;
import android.widget.ArrayAdapter;

class StoresAdapter extends ArrayAdapter<String> {

    public StoresAdapter(final Context context) {
        super(context, android.R.layout.simple_list_item_1);
    }
}
