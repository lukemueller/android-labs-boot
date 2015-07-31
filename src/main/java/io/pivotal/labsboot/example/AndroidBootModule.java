package io.pivotal.labsboot.example;

import android.content.Context;

import java.net.MalformedURLException;
import java.net.URL;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        HeroesListFragment.class
    },
    complete = false
)
public class AndroidBootModule {
    @Provides
    HeroesAsyncTaskLoader provideHeroesAsyncTaskLoader(Context context) {
        HeroesAsyncTaskLoader loader = new HeroesAsyncTaskLoader(context);
        try {
            loader.setUrl(new URL("http://marvelousapi.cfapps.io"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return loader;
    }
}