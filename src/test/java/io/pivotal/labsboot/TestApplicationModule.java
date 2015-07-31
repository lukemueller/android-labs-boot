package io.pivotal.labsboot;

import dagger.Module;

@Module(
        includes = {
                io.pivotal.labsboot.example.TestAndroidBootModule.class
        },
        complete = false
)
public class TestApplicationModule {

}
