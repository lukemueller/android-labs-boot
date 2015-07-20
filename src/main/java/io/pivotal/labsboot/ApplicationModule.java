package io.pivotal.labsboot;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.stores.StoresModule;

@Module(
        includes = {
                StoresModule.class
        },
        library = true,
        complete = false
)
public class ApplicationModule {
    private Context mContext;

    public ApplicationModule(final Context context) {
        mContext = context;
    }

    @Provides
    Context providesApplicationContext() {
        return mContext;
    }
}
