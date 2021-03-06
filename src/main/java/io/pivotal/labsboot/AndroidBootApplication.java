package io.pivotal.labsboot;

import android.app.Application;

import dagger.ObjectGraph;

public class AndroidBootApplication extends Application {
    private ObjectGraph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        createGraph();
    }

    protected void createGraph() {
        mGraph = ObjectGraph.create(getModules());
    }

    public Object[] getModules() {
        final Object[] modules = new Object[1];
        modules[0] = new ApplicationModule(this);
        return modules;
    }

    public void inject(Object object) {
        mGraph.inject(object);
    }
}
