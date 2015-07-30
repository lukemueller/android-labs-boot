package io.pivotal.labsboot.example;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class HeroesListAdapter extends BaseAdapter {
    private List<Hero> data;

    public HeroesListAdapter(List<Hero> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return convertView;
    }

    protected List<Hero> getData() {
        return data;
    }
}
