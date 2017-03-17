package wtf.a42.mopro.sw02.ui_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class LayoutDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String layoutType = getIntent().getStringExtra("layout");

        if(layoutType.equals("linear") )
        {
            setContentView(R.layout.layoutdemo_linearlayout);
        }
        else if(layoutType.equals("relative"))
        {
            setContentView(R.layout.layoutdemo_relativelayout);

        }
        else
        {
            setContentView(R.layout.activity_layout_demo);

        }
    }
}
