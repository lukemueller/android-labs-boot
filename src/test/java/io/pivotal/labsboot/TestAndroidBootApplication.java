package io.pivotal.labsboot;

public class TestAndroidBootApplication extends AndroidBootApplication {
    public Object[] getModules() {
        final Object[] modules = new Object[1];
        modules[0] = new TestApplicationModule();
        return modules;
    }
}