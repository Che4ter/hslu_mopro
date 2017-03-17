package ch.hslu.mobpro.firstappskeleton;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Logs lifecycle transitions into the LogCat view of the ADT-Debugger.
 *
 * @author Ruedi Arnold
 */

public class LifecycleLogActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onCreate() aufgerufen");

    }

    protected void onStart()
    {
        super.onStart();
        Log.i("hslu_mobApp", "onStart() aufgerufen");

    }

    protected void onRestart()
    {
        super.onRestart();
        Log.i("hslu_mobApp", "onRestart() aufgerufen");

    }

    protected void onResume()
    {
        super.onResume();
        Log.i("hslu_mobApp", "onResume() aufgerufen");

    }

    protected void onPause()
    {
        super.onPause();
        Log.i("hslu_mobApp", "onPause() aufgerufen");

    }

    protected void onStop()
    {
        super.onStop();
        Log.i("hslu_mobApp", "onStop() aufgerufen");

    }

    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("hslu_mobApp", "onDestroy() aufgerufen");

    }
    // TODO: Add further implementions of onX-methods.

}