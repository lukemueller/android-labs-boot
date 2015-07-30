package io.pivotal.labsboot.example;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.BaseAdapter;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBaseAdapter;
import org.robolectric.util.FragmentTestUtil;

import java.util.ArrayList;

import io.pivotal.labsboot.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroesListFragmentTest {


    @Test
    public void addsHeroesLoaderToLoaderManager() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment);

        LoaderManager loaderManager = fragment.getActivity().getLoaderManager();
        Loader<Object> loader = loaderManager.getLoader(1);

        Assert.assertEquals(loader.getClass(), HeroesAsyncTaskLoader.class);
    }

    @Test
    public void updatedDataIsSetOnAdapter() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment, Activity.class);

        // call the listener for the loader finished loading with a list of heroes
        ArrayList<Hero> heroes = new ArrayList<>();
        Hero hero = new Hero();
        hero.setName("Bob");
        heroes.add(hero);
        fragment.onLoadFinished(null, heroes);

        // assert that the adapters notify dataset changed was called with that same list of heroes
        BaseAdapter listAdapter = (BaseAdapter) fragment.getListAdapter();
        ShadowBaseAdapter shadowBaseAdapter = Shadows.shadowOf(listAdapter);
        Assert.assertTrue(shadowBaseAdapter.wasNotifyDataSetChangedCalled());
        Assert.assertEquals(fragment.getData().get(0).getName(), "Bob");

        HeroesListAdapter heroListAdapter = (HeroesListAdapter) fragment.getListAdapter();
        Assert.assertEquals(heroListAdapter.getData().get(0).getName(), "Bob");
    }
}