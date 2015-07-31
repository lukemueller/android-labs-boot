package io.pivotal.labsboot.example;

import android.app.Activity;
import android.content.Loader;
import android.widget.BaseAdapter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowBaseAdapter;
import org.robolectric.util.FragmentTestUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import io.pivotal.labsboot.AndroidBootApplication;
import io.pivotal.labsboot.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroesListFragmentTest {

    @Inject
    HeroesAsyncTaskLoader mockHeroesAsyncTaskLoader;

    @Before
    public void setup() {
        ((AndroidBootApplication) RuntimeEnvironment.application).inject(this);
    }

    @Test
    public void addsHeroesLoaderToLoaderManager() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment);

        Loader<Object> loader = fragment.getActivity().getLoaderManager().getLoader(1);

        Assert.assertEquals(mockHeroesAsyncTaskLoader, loader);
        Mockito.verify(mockHeroesAsyncTaskLoader).forceLoad();
    }

    @Test
    public void updatedDataIsSetOnAdapter() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment, Activity.class);

        ArrayList<Hero> heroes = new ArrayList<>();
        Hero hero = new Hero();
        hero.setName("Bob");
        heroes.add(hero);
        fragment.onLoadFinished(null, heroes);

        BaseAdapter listAdapter = (BaseAdapter) fragment.getListAdapter();
        ShadowBaseAdapter shadowBaseAdapter = Shadows.shadowOf(listAdapter);
        Assert.assertTrue(shadowBaseAdapter.wasNotifyDataSetChangedCalled());
        Assert.assertEquals(fragment.getData().get(0).getName(), "Bob");

        HeroesListAdapter heroListAdapter = (HeroesListAdapter) fragment.getListAdapter();
        Assert.assertEquals(heroListAdapter.getData().get(0).getName(), "Bob");
    }
}