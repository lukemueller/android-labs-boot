package io.pivotal.labsboot;

import io.pivotal.labsboot.example.TestAndroidBootModule;

public class TestAndroidBootApplication extends AndroidBootApplication {
    public Object[] getModules() {
        final Object[] modules = new Object[1];
        modules[0] = new TestAndroidBootModule();
        return modules;
    }
}