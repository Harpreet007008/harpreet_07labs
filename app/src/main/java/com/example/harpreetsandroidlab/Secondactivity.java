package com.example.harpreetsandroidlab;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

class SecondActivity extends AppCompatActivity {

    private ImageView profileImage;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        Intent fromPrevious = getIntent();
        String text = fromPrevious.getStringExtra("Info");

        float fromMain =fromPrevious.getFloatExtra("MyFloat", 0.0f);
        float test =fromPrevious.getFloatExtra("ABCD", 1.0F);
        boolean t =fromPrevious.getBooleanExtra("IsTrue", false);

        Button call= findViewById(R.id.button);
        call.setOnClickListener(view ->{
            
             finish();
                }
                );


        TextView top = findViewById(R.id.textView);
        top.setText("Myfloat="+ fromMain +"test="+test);

        EditText line = findViewById(R.id.editTextPhone);
        line.setText(text);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3456)
        {
            if(resultCode == RESULT_OK)
            {
                Bitmap thumbnail = data.getParcelableExtra("data");
                profileImage.setImageBitmap(thumbnail);
            }
        }

    }
}
