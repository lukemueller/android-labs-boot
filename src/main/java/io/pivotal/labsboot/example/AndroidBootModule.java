package io.pivotal.labsboot.example;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                AndroidBootActivity.class
        },
        library = true,
        complete = false
)
public class AndroidBootModule {
    @Provides
    AndroidBootDelegate providesDelegate(final Context context) {
        return new AndroidBootDelegate(context);
    }
}