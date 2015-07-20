package io.pivotal.labsboot;

import org.robolectric.RuntimeEnvironment;

public class TestInjector {
    public static void inject(final Object object) {
        ((TestBootApplication) RuntimeEnvironment.application).inject(object);
    }
}
