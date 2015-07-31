package io.pivotal.labsboot.example;

import android.view.View;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroesListAdapterTest {

    private Hero antMan;
    private Hero spiderMan;
    private Hero maryJane;

    private HeroesListAdapter emptyListAdapter;
    private HeroesListAdapter oneListAdapter;
    private HeroesListAdapter manyListAdapter;

    @Before
    public void setup() {
        ArrayList<Hero> empty = new ArrayList<>();
        ArrayList<Hero> one = new ArrayList<>();
        ArrayList<Hero> many = new ArrayList<>();

        antMan = new Hero();
        antMan.setName("Ant-Man");

        spiderMan = new Hero();
        spiderMan.setName("Spider-Man");

        maryJane = new Hero();
        maryJane.setName("Mary Jane");

        one.add(antMan);
        many.add(spiderMan);
        many.add(maryJane);

        emptyListAdapter = new HeroesListAdapter(RuntimeEnvironment.application, empty);
        oneListAdapter = new HeroesListAdapter(RuntimeEnvironment.application, one);
        manyListAdapter = new HeroesListAdapter(RuntimeEnvironment.application, many);
    }

    @Test
    public void testGetCount() {
        Assert.assertEquals(0, emptyListAdapter.getCount());
        Assert.assertEquals(1, oneListAdapter.getCount());
        Assert.assertTrue(manyListAdapter.getCount() > 1);
    }

    @Test
    public void testGetItem() {
        Assert.assertEquals(null, emptyListAdapter.getItem(0));
        Assert.assertEquals(antMan, oneListAdapter.getItem(0));
        Assert.assertEquals(spiderMan, manyListAdapter.getItem(0));
        Assert.assertEquals(maryJane, manyListAdapter.getItem(1));
        Assert.assertEquals(null, manyListAdapter.getItem(100));
    }

    @Test
    public void testGetItemId() {
        Assert.assertEquals(0, emptyListAdapter.getItemId(0));
        Assert.assertEquals(16, emptyListAdapter.getItemId(16));
    }

    @Test
    public void testGetView_nullConvertView() {
        View view = oneListAdapter.getView(0, null, null);
        TextView nameTextView = (TextView) view.findViewById(R.id.hero_list_item_name_text_label);

        Assert.assertEquals("Ant-Man", nameTextView.getText().toString());
    }

    @Test
    public void testGetView_presentConvertView() {
        View viewToBeRecycled = oneListAdapter.getView(0, null, null);
        View recycledView = oneListAdapter.getView(0, viewToBeRecycled, null);

        Assert.assertEquals(recycledView, viewToBeRecycled);
    }
}