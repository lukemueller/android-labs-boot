package io.pivotal.labsboot.stores;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                StoresFragment.class
        },
        library = true,
        complete = false
)
public class StoresModule {
    @Provides
    StoresDelegate providesDelegate(final Context context) {
        return new StoresDelegate(context);
    }

    @Provides
    StoresAdapter providesAdapter(final Context context) {
        return new StoresAdapter(context);
    }
}