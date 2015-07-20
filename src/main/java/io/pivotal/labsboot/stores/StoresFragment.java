package io.pivotal.labsboot.stores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import io.pivotal.labsboot.InjectableFragment;
import io.pivotal.labsboot.R;

public class StoresFragment extends InjectableFragment {
    @Inject StoresDelegate mStoresDelegate;
    @Inject StoresAdapter mStoresAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stores, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        final List<String> strings = mStoresDelegate.fetchData();
        mStoresAdapter.clear();
        mStoresAdapter.addAll(strings);
        getListView().setAdapter(mStoresAdapter);
    }

    public ListView getListView() {
        return (ListView) getView().findViewById(R.id.fragment_stores_list);
    }
}
