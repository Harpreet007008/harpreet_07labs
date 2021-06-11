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

        EditText phonenumber = findViewById(R.id.editTextPhone);
        Editable phonenumberText = phonenumber.getText();
        TextView Topofthescreen = findViewById(R.id.textView);

        Intent fromPrevious = getIntent();
        String text = fromPrevious.getStringExtra("EmailAddress");
        Topofthescreen.setText("Welcome Back:" + text);

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener(clk ->{
            Intent next = new Intent(Intent.ACTION_DIAL);
            next.setData(Uri.parse("tel:" + phonenumberText));
            startActivity(next);
        });
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(clk -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult( cameraIntent, 3456);
        });

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
