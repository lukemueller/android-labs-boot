package io.pivotal.labsboot.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import io.pivotal.labsboot.AndroidBootApplication;
import io.pivotal.labsboot.R;

class AndroidBootActivity extends Activity {

    @Inject
    protected AndroidBootDelegate mAndroidBootDelegate;

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
        final String result = mAndroidBootDelegate.doSomething("what am i doing?");
        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(result);
    }
}
