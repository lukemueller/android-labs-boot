package io.pivotal.labsboot.example;

import android.app.Activity;
import android.os.Bundle;

import io.pivotal.labsboot.R;

public class AndroidBootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
