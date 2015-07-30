package io.pivotal.labsboot.example;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import io.pivotal.labsboot.BuildConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroesListAdapterTest {

    ArrayList<Hero> empty = new ArrayList<>();
    ArrayList<Hero> one = new ArrayList<>();
    ArrayList<Hero> many = new ArrayList<>();

    @Before
    public void setup() {
        one.add(new Hero());
        many.add(new Hero());
        many.add(new Hero());
    }

    @Test
    public void testGetCount() throws Exception {
        assertEquals(0, new HeroesListAdapter(empty).getCount());
        assertEquals(1, new HeroesListAdapter(one).getCount());
        assertTrue(new HeroesListAdapter(many).getCount() > 1);
    }

    @Test
    public void testGetItem() throws Exception {
        HeroesListAdapter heroesListAdapter = new HeroesListAdapter(many);

        assertEquals(many.get(0), heroesListAdapter.getItem(0));
        assertEquals(many.get(1), heroesListAdapter.getItem(1));
    }

    @Test
    public void testRecycleView() throws Exception {
        HeroesListAdapter heroesListAdapter = new HeroesListAdapter(many);

        View convertView = new View(RuntimeEnvironment.application);
        View view = heroesListAdapter.getView(0, convertView, null);

        assertEquals(convertView, view);
    }
}