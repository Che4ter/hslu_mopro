package wtf.a42.mopro.sw03.persistenz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    public static final String PREFS_NAME = "PersistenzPreferences";
    public static final String FILENAME = "note_file.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int numberOfResumeCalls = settings.getInt("onResumeCalls", 0);
        setOnResumeNumbers(numberOfResumeCalls);

        setTeePreferences();

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {

            ((CheckBox) this.findViewById(R.id.checkbox_externalstorage)).setEnabled(false);
        }
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
        setTeePreferences();

    }

    public void setOnResumeNumbers(int numberOfCalls) {
        TextView resumeCountTextview = (TextView) this.findViewById(R.id.textViewResumes);
        String myFormattedString = String.format(getString(R.string.onresume_number), numberOfCalls);
        resumeCountTextview.setText(myFormattedString);
    }

    public void setTeePreferences() {
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        TextView teePreferences = (TextView) this.findViewById(R.id.textViewTeePreferences);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String teepref = " ungesüsst";
        if (prefs.getBoolean("check_box_preference_tee_gesuesst", false)) {
            teepref = " gesüsst mit " + prefs.getString("list_preference_tee_suessstoff", "Honig");

        }
        teePreferences.setText(getString(R.string.ich_trinke_am_liebsten) + prefs.getString("edit_text_preference_tee_marke", "Mate Tee") + teepref);

    }

    public void editPreferencesOnClick(View view) {


        Intent intent = new Intent(this, AppPreferenceActivity.class);
        startActivity(intent);
    }

    public void setDefaultPreferencesOnClick(View view) {
        PreferenceManager.getDefaultSharedPreferences(this).edit().clear().commit();
        PreferenceManager.setDefaultValues(this, R.xml.preferences, true);
        setTeePreferences();
        Toast toast = Toast.makeText(this, "Settings wurden wiederhergestellt", Toast.LENGTH_SHORT);
        toast.show();

    }

    public void loadButtonOnClick(View view) {
        String filename = "note_file";
        String line;


        FileInputStream inputStream;
        try {
            if (((CheckBox) this.findViewById(R.id.checkbox_externalstorage)).isChecked()) {
                if (isExternalStorageAvailable() && !isExternalStorageReadOnly()) {
                    File directoryFile = new File(getExternalFilesDir(""), "");
                    directoryFile.mkdirs();
                    File outFile = new File(directoryFile, FILENAME);
                    inputStream = new FileInputStream(outFile);

                } else {
                    Toast toast = Toast.makeText(this, "External Storage not available", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } else {
                inputStream = openFileInput(filename);
            }
            InputStreamReader InputRead = new InputStreamReader(inputStream);

            char[] inputBuffer = new char[100];
            String note = "";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                note += readstring;
            }
            InputRead.close();

            EditText text = (EditText) this.findViewById(R.id.editText);
            text.setText(note);

        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Fehler beim Laden", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }

    public void saveButtonOnClick(View view) {
        FileOutputStream outputStream;
        try {
            if (((CheckBox) this.findViewById(R.id.checkbox_externalstorage)).isChecked()) {
                if ((isExternalStorageAvailable() && !isExternalStorageReadOnly())) {
                    File directoryFile = new File(getExternalFilesDir(""), "");
                    directoryFile.mkdirs();
                    File outFile = new File(directoryFile, FILENAME);
                    outputStream = new FileOutputStream(outFile);

                } else {
                    Toast toast = Toast.makeText(this, "External Storage not available", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            } else {
                outputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            }
            outputStream.write(((EditText) this.findViewById(R.id.editText)).getText().toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            Toast toast = Toast.makeText(this, "Fehler beim speichern", Toast.LENGTH_SHORT);
            toast.show();
            e.printStackTrace();
        }
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {

            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}
