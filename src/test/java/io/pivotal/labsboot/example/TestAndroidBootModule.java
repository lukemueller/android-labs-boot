package io.pivotal.labsboot.example;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module(
        injects = {
                AndroidBootActivity.class,
                AndroidBootActivityTest.class,
        },
        library = true,
        complete = false
)
public class TestAndroidBootModule {

    public TestAndroidBootModule() {}

    @Provides
    @Singleton
    AndroidBootDelegate providesClient() {
        return mock(AndroidBootDelegate.class);
    }
}
