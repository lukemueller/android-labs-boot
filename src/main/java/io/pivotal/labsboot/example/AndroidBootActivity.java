package io.pivotal.labsboot.example;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import javax.inject.Inject;

import io.pivotal.labsboot.AndroidBootApplication;
import io.pivotal.labsboot.R;

public class AndroidBootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AndroidBootApplication application = (AndroidBootApplication) getApplication();
        application.inject(this);
        setContentView(R.layout.activity_android_boot);
    }

    @Override
    protected void onStart() {
        super.onStart();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.heroes_list_fragment_container, new HeroesListFragment())
                .addToBackStack(null)
                .commit();
    }
}
