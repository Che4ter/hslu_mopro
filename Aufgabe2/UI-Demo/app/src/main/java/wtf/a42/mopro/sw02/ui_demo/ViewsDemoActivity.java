package wtf.a42.mopro.sw02.ui_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ViewsDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views_demo);



        RatingBar rating = (RatingBar) this.findViewById(R.id.ratingBar);
        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                TextView t = (TextView) ViewsDemoActivity.this.findViewById(R.id.txtRating);
                t.setText("Aktuelles Rating: " + new Float(rating).toString());

            }
        });

        TextView t = (TextView) ViewsDemoActivity.this.findViewById(R.id.txtRating);

        t.setText("Aktuelles Rating: " + new Float(rating.getRating()).toString());


        ToggleButton tbutton = (ToggleButton) this.findViewById(R.id.toggleButton);
        tbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    ProgressBar p = (ProgressBar) ViewsDemoActivity.this.findViewById(R.id.progressBar);
                    p.setVisibility(View.VISIBLE);
                }
                else
                {
                    ProgressBar p = (ProgressBar) ViewsDemoActivity.this.findViewById(R.id.progressBar);
                    p.setVisibility(View.INVISIBLE);
                }

            }
        });

        Button testbutton = (Button) this.findViewById(R.id.button);
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Button Clicked :)";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
