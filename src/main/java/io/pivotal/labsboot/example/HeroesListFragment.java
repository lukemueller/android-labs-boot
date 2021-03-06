package io.pivotal.labsboot.example;

import android.app.Activity;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.pivotal.labsboot.AndroidBootApplication;
import io.pivotal.labsboot.R;

public class HeroesListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<Hero>> {

    private BaseAdapter adapter;
    private List<Hero> data;

    @Inject
    HeroesAsyncTaskLoader heroAsyncTaskLoader;

    public HeroesListFragment() {
        this.data = new ArrayList<>();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((AndroidBootApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hero_list_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        adapter = new HeroesListAdapter(getActivity(), data);
        setListAdapter(adapter);

        LoaderManager loaderManager = getActivity().getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }

    @Override
    public Loader<List<Hero>> onCreateLoader(int id, Bundle args) {
        heroAsyncTaskLoader.forceLoad();
        return heroAsyncTaskLoader;
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
