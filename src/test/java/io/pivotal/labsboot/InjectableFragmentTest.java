package io.pivotal.labsboot;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class InjectableFragmentTest {

    @Test
    public void onAttach_injectsFragment() {
        final InjectableFragment injectableFragment = new InjectableFragment() {
        };
        final Activity mockActivity = mock(Activity.class);
        final TestBootApplication mockApplication = mock(TestBootApplication.class);
        doReturn(mockApplication).when(mockActivity).getApplication();

        injectableFragment.onAttach(mockActivity);

        verify(mockApplication).inject(injectableFragment);
    }
}