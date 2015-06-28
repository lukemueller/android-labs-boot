package io.pivotal.labsboot.example;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                AndroidBootActivity.class,
        },
        library = true,
        complete = false
)
public class AndroidBootModule {
    private final Context mContext;

    public AndroidBootModule(final Context context) {
        mContext = context;
    }
    @Provides
    AndroidBootDelegate providesClient() {
        return new AndroidBootDelegate(mContext);
    }
}
