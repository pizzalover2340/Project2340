package gitmad.gatech.edu.project2340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RegActivity extends AppCompatActivity {
    private EditText signUpName;
    private EditText signUpPassword;
    private Spinner signUpUserType;
    private Button confirmButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        signUpName = (EditText) findViewById(R.id.username);
        signUpPassword = (EditText) findViewById(R.id.password);

        signUpUserType = (Spinner) findViewById(R.id.userSpinner);
        List<String> list = new ArrayList<String>();
        list.add("User");
        list.add("Admin");
        list.add("Location Employee");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signUpUserType.setAdapter(dataAdapter);


        confirmButton = (Button)findViewById(R.id.confirmButton);
        final Intent loginpage = new Intent(this, LoginActivity.class);

        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 String username = signUpName.getText().toString();
                 String password = signUpPassword.getText().toString();
                 //String usertype = signUpUserType.getSelectedItem().toString();
                    loginpage.putExtra("name", username);
                    loginpage.putExtra("pass", password);
                    //loginpage.putExtra("usertype", usertype);
                    startActivity(new Intent(loginpage));

            }
        });
    }
}
