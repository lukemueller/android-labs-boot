package io.pivotal.labsboot.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import io.pivotal.labsboot.R;

public class HeroListActivity extends Activity implements HeroListListener{

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

    @Override
    public void onItemClick(String dataURL) {
        Bundle bundle = new Bundle();
        bundle.putString(HeroDetailsActivity.HERO_DETAILS_URL, dataURL);

        startActivity(new Intent(this, HeroDetailsActivity.class), bundle);
    }
}
