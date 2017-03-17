package wtf.a42.mopro.sw03.persistenz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.widget.TextView;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "PersistenzPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int numberOfResumeCalls = settings.getInt("onResumeCalls", 0);
        setOnResumeNumbers(numberOfResumeCalls);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        int numberOfResumeCalls = settings.getInt("onResumeCalls", 0);
        numberOfResumeCalls++;
        editor.putInt("onResumeCalls", numberOfResumeCalls);

        // Commit the edits!
        editor.commit();
        setOnResumeNumbers(numberOfResumeCalls);

    }

    public void setOnResumeNumbers(int numberOfCalls)
    {
        TextView resumeCountTextview = (TextView) this.findViewById(R.id.textViewResumes);
        String myFormattedString = String.format(getString(R.string.onresume_number), numberOfCalls);
        resumeCountTextview.setText(myFormattedString);
    }
}
