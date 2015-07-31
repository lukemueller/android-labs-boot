package io.pivotal.labsboot.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import io.pivotal.labsboot.R;

public class HeroesListAdapter extends BaseAdapter {
    private Context context;
    private List<Hero> data;

    public HeroesListAdapter(Context context, List<Hero> data) {
        this.context = context;
        this.data = data;
    }

    protected List<Hero> getData() {
        return data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if (position >= getCount()) {
            return null;
        }

        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hero_list_item, parent, false);
        }

        Hero hero = (Hero) getItem(position);
        TextView textView = (TextView) view.findViewById(R.id.hero_list_item_name_text_label);
        textView.setText(hero.getName());

        return view;
    }
}
