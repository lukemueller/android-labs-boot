package io.pivotal.labsboot;

import android.app.Activity;
import android.app.Fragment;

public abstract class InjectableFragment extends Fragment {

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);

        ((BootApplication) activity.getApplication()).inject(this);
    }
}
