package com.example.harpreetsandroidlab;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {


    private EditText passwordText;


    private TextView textView;


    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordText = findViewById(R.id.pw);
        textView = findViewById(R.id.textView);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener( clk -> {
            String password = passwordText.getText().toString();

            if(checkPassword( password ))//check if password is complex enough
            {
                textView.setText("Your password has ABC");
            }
            else textView.setText("No ABC string was found");


        });
    }


    private boolean checkPassword(String password) {
        return password.contains("ABC");
    }
}