package io.pivotal.labsboot.example;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class HeroesAsyncTaskLoader extends AsyncTaskLoader<List<Hero>>{
    public HeroesAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public List<Hero> loadInBackground() {
        return null;
    }
}
