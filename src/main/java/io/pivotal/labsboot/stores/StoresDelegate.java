package io.pivotal.labsboot.stores;

import android.content.Context;
import android.content.res.Resources;

import java.util.Arrays;
import java.util.List;

import io.pivotal.labsboot.R;

class StoresDelegate {
    private Context mContext;

    public StoresDelegate(final Context context) {
        mContext = context;
    }

    public List<String> fetchData() {
        final Resources resources = mContext.getResources();
        return Arrays.asList(resources.getStringArray(R.array.fragment_stores_data));
    }
}
