package com.example.harpreetsandroidlab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.CompoundButtonCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loard object
        TextView mytext = findViewById(R.id.mytextview);

        Button myButton = findViewById(R.id.mybutton);

        myButton.setOnClickListener((vw) ->
        {
            mytext.setText("You clicked the button");
        });


        CheckBox mycheckbox = findViewById(R.id.mycb);


        mycheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });

        mycheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            Context context = getApplicationContext();
            CharSequence text = "Hello toast!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

        });


        RadioButton radiobutton = findViewById(R.id.myradio);
        radiobutton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked)
                myButton.setText("radio is on");
            else
                myButton.setText("radio is off");


        });

        ImageButton imagebutton = findViewById(R.id.animage);


    }
}


