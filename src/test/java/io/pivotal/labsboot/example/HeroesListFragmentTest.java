package io.pivotal.labsboot.example;

import android.app.Activity;
import android.content.Loader;
import android.widget.BaseAdapter;
import android.widget.ListView;

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
        FragmentTestUtil.startFragment(fragment, FakeListListenerActivity.class);

        Loader<Object> loader = fragment.getActivity().getLoaderManager().getLoader(1);

        Assert.assertEquals(mockHeroesAsyncTaskLoader, loader);
        Mockito.verify(mockHeroesAsyncTaskLoader).forceLoad();
    }

    @Test
    public void updatedDataIsSetOnAdapter() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment, FakeListListenerActivity.class);

        ArrayList<Hero> heroes = new ArrayList<>();
        Hero hero = new Hero();
        hero.setName("Bob");
        hero.setDetailUrl("http://marvelous.api/example");
        heroes.add(hero);
        fragment.onLoadFinished(null, heroes);

        BaseAdapter listAdapter = (BaseAdapter) fragment.getListAdapter();
        ShadowBaseAdapter shadowBaseAdapter = Shadows.shadowOf(listAdapter);
        Assert.assertTrue(shadowBaseAdapter.wasNotifyDataSetChangedCalled());
        Assert.assertEquals(fragment.getData().get(0).getName(), "Bob");
        Assert.assertEquals(fragment.getData().get(0).getDetailUrl(), "http://marvelous.api/example");
    }

    @Test
    public void notifiesActivityOfItemClick() {
        HeroesListFragment fragment = new HeroesListFragment();
        FragmentTestUtil.startFragment(fragment, FakeListListenerActivity.class);

        ArrayList<Hero> heroes = new ArrayList<>();
        Hero hero = new Hero();
        hero.setDetailUrl("http://marvelous.api.notreal/heroes/12345");
        heroes.add(hero);
        fragment.onLoadFinished(null, heroes);

        ListView listView = fragment.getListView();
        Shadows.shadowOf(listView).performItemClick(0);

        FakeListListenerActivity fakeHeroListListenerActivity = (FakeListListenerActivity) fragment.getActivity();
        Assert.assertEquals(1, fakeHeroListListenerActivity.getOnItemClickCallCount());
        Assert.assertEquals("http://marvelous.api.notreal/heroes/12345", fakeHeroListListenerActivity.getOnItemClickArg());
    }

    private static class FakeListListenerActivity extends Activity implements HeroListListener {
        private int onItemClickCallCount;
        private String onItemClickArg;

        @Override
        public void onItemClick(String dataURL) {
            onItemClickArg = dataURL;
            onItemClickCallCount++;
        }

        public int getOnItemClickCallCount() {
            return onItemClickCallCount;
        }

        public String getOnItemClickArg() {
            return onItemClickArg;
        }
    }
}