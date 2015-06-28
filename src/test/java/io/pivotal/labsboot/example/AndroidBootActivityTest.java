package io.pivotal.labsboot.example;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import javax.inject.Inject;

import io.pivotal.labsboot.BuildConfig;
import io.pivotal.labsboot.R;
import io.pivotal.labsboot.TestAndroidBootApplication;

import static org.fest.assertions.api.ANDROID.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants=BuildConfig.class)
public class AndroidBootActivityTest {

    @Inject
    AndroidBootDelegate mAndroidBootDelegate;

    @Before
    public void setup() {
        final TestAndroidBootApplication application = (TestAndroidBootApplication) RuntimeEnvironment.application;
        application.inject(this);
    }

    @Test
    public void onStartingActivity_presentsDataFromDelegate() throws Exception {
        doReturn("testing!").when(mAndroidBootDelegate).doSomething(anyString());

        final ActivityController<AndroidBootActivity> activityController = Robolectric.buildActivity(AndroidBootActivity.class).create();

        verifyZeroInteractions(mAndroidBootDelegate);

        activityController.start();

        verify(mAndroidBootDelegate).doSomething("what am i doing?");

        final AndroidBootActivity activity = activityController.get();

        final TextView view = (TextView) activity.findViewById(R.id.text);
        assertThat(view).hasText("testing!");
    }
}
