package wtf.a42.mopro.sw02.ui_demo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        RadioGroup layoutGroup = (RadioGroup) this.findViewById(R.id.rdLayoutDemo);
        layoutGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String layout = "";
                if (checkedId == R.id.radioButtonLinearLayout) {
                    layout = "linear";
                } else if (checkedId == R.id.radioButtonRelativeLayout) {
                    layout = "relative";
                }
                Intent intent = new Intent(MainActivity.this,LayoutDemoActivity.class);
                intent.putExtra("layout",layout);
                startActivity(intent);
            }
        });

        Button showDemoViews = (Button) this.findViewById(R.id.buttonDemoView);
        showDemoViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewsDemoActivity.class);
                startActivity(intent);
            }
        });

        Spinner mySpinner = (Spinner) this.findViewById(R.id.spinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                CharSequence text = parent.getItemAtPosition(position).toString();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
}
