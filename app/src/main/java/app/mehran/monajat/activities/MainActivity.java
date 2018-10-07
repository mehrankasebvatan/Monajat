package app.mehran.monajat.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.mehran.monajat.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity {

    private CardView amir;
    private TextView textView;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amir = findViewById(R.id.Amir);
        amir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent amir = new Intent(MainActivity.this,AmirActivity.class);
                startActivity(amir);
                finish();
            }
        });

        textView = findViewById(R.id.Text_toolbar);
        textView.setText(R.string.app_name);


    }
}
