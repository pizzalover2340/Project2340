package gitmad.gatech.edu.project2340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class testActivity extends AppCompatActivity {
    private Button log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //log = (Button)findViewById(R.id.logout);
        final Intent logPage = new Intent(this, LoginActivity.class);
        log.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(logPage));
            }
        });
    }
}
