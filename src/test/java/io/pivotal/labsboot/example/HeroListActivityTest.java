package io.pivotal.labsboot.example;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowIntent;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;

import static org.fest.assertions.api.Assertions.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class HeroListActivityTest {

    @Test
    public void onStartingActivity_presentsDataFromDelegate() throws Exception {
        HeroListActivity activity = Robolectric.setupActivity(HeroListActivity.class);

        FragmentManager fragmentManager = activity.getFragmentManager();

        Assert.assertEquals(fragmentManager.getBackStackEntryCount(), 1);
        Fragment heroesListFragment = fragmentManager.findFragmentById(R.id.heroes_list_fragment_container);
        assertThat(heroesListFragment).isNotNull();
        Assert.assertEquals(heroesListFragment.getClass(), HeroesListFragment.class);
    }

    @Test
    public void onItemClickListenerCreatesNewActivity() {
        HeroListActivity activity = Robolectric.setupActivity(HeroListActivity.class);

        activity.onItemClick("Hello!");

        Intent intent = Shadows.shadowOf(activity).getNextStartedActivity();
        ShadowIntent shadowIntent = Shadows.shadowOf(intent);
        assertThat(shadowIntent.getIntentClass().getName()).isEqualTo(HeroDetailsActivity.class.getName());
        assertThat(intent.getStringExtra(HeroDetailsActivity.HERO_DETAILS_URL)).isEqualTo("Hello!");
        assertThat(shadowIntent.getStringExtra(HeroDetailsActivity.HERO_DETAILS_URL)).isEqualTo("Hello!");
    }
}
