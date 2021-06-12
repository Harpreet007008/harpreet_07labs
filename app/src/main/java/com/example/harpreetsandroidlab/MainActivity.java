package com.example.harpreetsandroidlab;

import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginBtn = findViewById(R.id.nextPageButton);
        Log.v(TAG , "In onCreate() - Loading Widgets" );


        loginBtn.setOnClickListener( clk -> {
            EditText et = findViewById(R.id.inputEditText);

            //This Intent say that you want to transition from MainActivity to SecondActivity

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra("some Info", et.getText().toString());
            nextPage.putExtra("MyFloat", 3.14f);
            nextPage.putExtra("IsTrue", false);
            Intent call =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivity( nextPage   );
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123){
            Log.w(TAG, "coming back from next page");

        }
    }



    @Override  //screen is now visible
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "In onCreate() - The application is now visible on screen" );
    }

    @Override  //screen is now listening for input
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "In onCreate() - The application is now responding to user input" );
    }


}