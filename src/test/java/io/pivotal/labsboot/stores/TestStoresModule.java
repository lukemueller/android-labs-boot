package io.pivotal.labsboot.stores;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@Module(
        injects = {
                StoresFragment.class,
                StoresFragmentTest.class
        },
        library = true,
        complete = false,
        overrides = true
)
public class TestStoresModule {
    @Provides
    @Singleton
    StoresDelegate providesDelegate() {
        return mock(StoresDelegate.class);
    }

    @Provides
    @Singleton
    StoresAdapter providesAdapter(final Context context) {
        return spy(new StoresAdapter(context));
    }
}
