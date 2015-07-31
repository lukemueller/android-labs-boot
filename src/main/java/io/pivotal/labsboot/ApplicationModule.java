package io.pivotal.labsboot;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import io.pivotal.labsboot.example.AndroidBootModule;

@Module(
    includes = {
        AndroidBootModule.class
    },
    complete = false,
    library = true
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
