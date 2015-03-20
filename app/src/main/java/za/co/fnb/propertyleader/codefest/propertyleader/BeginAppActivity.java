package za.co.fnb.propertyleader.codefest.propertyleader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BeginAppActivity extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 3000;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final Intent intent;
        intent = new Intent().setClass(this, HomeActivity.class);
        // thread for displaying the SplashScreen
        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    finish();
                    startActivity(intent);
                    stopService(new Intent().setClass(BeginAppActivity.this, BeginAppActivity.class));
                }
            }
        };
        splashTread.start();
    }
}
