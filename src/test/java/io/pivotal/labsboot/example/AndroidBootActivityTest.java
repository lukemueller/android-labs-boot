package io.pivotal.labsboot.example;

import android.app.Fragment;
import android.app.FragmentManager;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;

import static org.fest.assertions.api.ANDROID.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AndroidBootActivityTest {

    @Test
    public void onStartingActivity_presentsDataFromDelegate() throws Exception {
        AndroidBootActivity activity = Robolectric.setupActivity(AndroidBootActivity.class);

        FragmentManager fragmentManager = activity.getFragmentManager();

        Assert.assertEquals(fragmentManager.getBackStackEntryCount(), 1);
        Fragment heroesListFragment = fragmentManager.findFragmentById(R.id.heroes_list_fragment_container);
        assertThat(heroesListFragment).isNotNull();
        Assert.assertEquals(heroesListFragment.getClass(), HeroesListFragment.class);
    }
}
