package io.pivotal.labsboot.example;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeroesListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Hero>> {

    private BaseAdapter adapter;
    private List<Hero> data;

    public HeroesListFragment() {
        this.data = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter = new HeroesListAdapter(data);
        setListAdapter(adapter);

        LoaderManager loaderManager = getActivity().getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<Hero>> onCreateLoader(int id, Bundle args) {
        return new HeroesAsyncTaskLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Hero>> loader, List<Hero> data) {
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    protected List<Hero> getData() {
        return data;
    }
}
