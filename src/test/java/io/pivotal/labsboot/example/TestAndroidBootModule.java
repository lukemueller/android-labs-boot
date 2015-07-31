package io.pivotal.labsboot.example;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    injects = {
        HeroesListFragment.class,
        HeroesListFragmentTest.class,
    },
    complete = false,
    overrides = true
)

public class TestAndroidBootModule {
    @Provides
    @Singleton
    HeroesAsyncTaskLoader provideHeroesAsyncTaskLoader() {
        return Mockito.mock(HeroesAsyncTaskLoader.class);
    }

}
