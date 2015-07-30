package io.pivotal.labsboot.example;

import android.app.Fragment;
import android.app.FragmentManager;
import android.widget.ListView;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.TestAndroidBootApplication;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class AndroidBootActivityTest {

    @Before
    public void setup() {
        final TestAndroidBootApplication application = (TestAndroidBootApplication) RuntimeEnvironment.application;
        application.inject(this);
    }

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
