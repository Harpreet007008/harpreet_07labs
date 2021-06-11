package com.example.harpreetsandroidlab;

import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.os.Bundle;
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
        Log.v("MainActivity", "In onCreate() - Loading Widgets" );

        Button loginBtn = findViewById(R.id.nextPageButton);
        EditText emailEditText =findViewById(R.id.inputEditText);

        loginBtn.setOnClickListener( clk -> {

            //This Intent say that you want to transition from MainActivity to SecondActivity

            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString() );

            startActivity( nextPage );
        });
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