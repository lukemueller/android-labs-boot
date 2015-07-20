package io.pivotal.labsboot.stores;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.TestInjector;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class StoresFragmentTest {
    @Inject StoresDelegate mStoresDelegate;
    @Inject StoresAdapter mStoresAdapter;
    private StoresFragment mStoresFragment;

    @Before
    public void setup() {
        TestInjector.inject(this);
        mStoresFragment = new StoresFragment();
    }

    @Test
    public void onFragmentCreated_inflatesLayout() {
        final LayoutInflater mockLayoutInflater = mock(LayoutInflater.class);
        final ViewGroup mockViewGroup = mock(ViewGroup.class);

        mStoresFragment.onCreateView(mockLayoutInflater, mockViewGroup, null);

        verify(mockLayoutInflater).inflate(R.layout.fragment_stores, mockViewGroup, false);
    }

    @Test
    public void onFragmentStarted_runsDelegate() {
        final List<String> stringList = Arrays.asList("one", "two", "three", "four");
        doReturn(stringList).when(mStoresDelegate).fetchData();
        FragmentTestUtil.startFragment(mStoresFragment);

        verify(mStoresAdapter).addAll(stringList);
        verify(mStoresDelegate).fetchData();
        assertThat(mStoresFragment.getListView()).hasAdapter(mStoresAdapter);
    }

    @Test
    public void onFragmentPausedAndResumed_stringsNotReAdded() {
        final List<String> stringList = Arrays.asList("one", "two", "three", "four");
        doReturn(stringList).when(mStoresDelegate).fetchData();
        FragmentTestUtil.startFragment(mStoresFragment);

        mStoresFragment.onPause();
        mStoresFragment.onStart();
        assertThat(mStoresFragment.getListView()).hasCount(4);
    }
}