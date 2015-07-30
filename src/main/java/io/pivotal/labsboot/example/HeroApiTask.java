package io.pivotal.labsboot.example;

import android.os.AsyncTask;

import java.util.List;

public class HeroApiTask extends AsyncTask<String, Void, List<Hero>> {
    @Override
    protected List<Hero> doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Hero> heros) {
        super.onPostExecute(heros);
    }
}
