package io.pivotal.labsboot.stores;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import io.pivotal.labsboot.BuildConfig;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class StoresDelegateTest {
    private StoresDelegate mStoresDelegate;

    @Before
    public void setup() {
        mStoresDelegate = new StoresDelegate(RuntimeEnvironment.application);
    }

    @Test
    public void fetchData_returnsList() {
        final List<String> strings = mStoresDelegate.fetchData();

        assertThat(strings).containsExactly("this", "is", "amazing", "testing");
    }
}