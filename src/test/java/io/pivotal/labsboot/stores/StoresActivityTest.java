package io.pivotal.labsboot.stores;

import android.app.Fragment;
import android.app.FragmentManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class StoresActivityTest {

    @Test
    public void onCreate_hasStoreFragment() {
        final StoresActivity storesActivity = Robolectric.setupActivity(StoresActivity.class);

        final FragmentManager fragmentManager = storesActivity.getFragmentManager();
        final Fragment fragment = fragmentManager.findFragmentById(R.id.activity_stores_content_frame);
        assertThat(fragment).isInstanceOf(StoresFragment.class);
    }
}
