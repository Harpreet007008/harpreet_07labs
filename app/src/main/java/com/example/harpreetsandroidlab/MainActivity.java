package com.example.harpreetsandroidlab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;



public class MainActivity extends AppCompatActivity {
    /**
     * this holds the text at the center of the screen
     */
    TextView tv = null;
    /**
     * this holds the editText as the password
     */
    EditText et = null;
    /**
     * this holds the button as login
     */
    Button btn = null;

    Bitmap image = null;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textview = findViewById(R.id.textView);
        EditText cityText = findViewById(R.id.cityTextField);
        Button forecastBtn = findViewById(R.id.forecastButton);

        forecastBtn.setOnClickListener(click -> {
            Executor newThread = Executors.newSingleThreadExecutor();
            newThread.execute( () -> {
                /* This runs in a separate thread */
                try {

                    String cityName = cityText.getText().toString();
                    String stringURL = "https://api.openweathermap.org/data/2.5/weather?q="
                            + URLEncoder.encode(cityName, "UTF-8")
                            + "&appid=7e943c97096a9784391a981c4d878b22&units=metric";

                    URL url = new URL(stringURL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    String text = (new BufferedReader(
                            new InputStreamReader(in, StandardCharsets.UTF_8)))
                            .lines()
                            .collect(Collectors.joining("\n"));


                    JSONObject theDocument = new JSONObject( text );
                    //JSONArray theArray = new JSONArray( text );

                    JSONObject coord = theDocument.getJSONObject( "coord" );
                    JSONArray weatherArray = theDocument.getJSONArray ( "weather" );
                    JSONObject position0 = weatherArray.getJSONObject(0);
                    String description = position0.getString("description");
                    String iconName = position0.getString("icon");
                    //String description = position0.getString("description");
                    //int vis = theDocument.getInt("visibility");
                    //String name = theDocument.getString( "name" );

                    JSONObject mainObject = theDocument.getJSONObject( "main" );
                    double current = mainObject.getDouble("temp");
                    double min = mainObject.getDouble("temp_min");
                    double max = mainObject.getDouble("temp_max");
                    int humitidy = mainObject.getInt("humidity");

                    URL imgUrl = new URL( "https://openweathermap.org/img/w/" + iconName + ".png" );
                    HttpURLConnection connection = (HttpURLConnection) imgUrl.openConnection();
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        image = BitmapFactory.decodeStream(connection.getInputStream());


                    }

                    FileOutputStream fOut = null;
                    try {
                        fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                        image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    }

                    Bitmap finalImage = image;
                    runOnUiThread( () -> {
                        TextView tv = findViewById(R.id.temp);
                        tv.setText("The current temperature is " + current);
                        tv.setVisibility(View.VISIBLE);

                        tv = findViewById(R.id.minTemp);
                        tv.setText("The min temperature is " + current);
                        tv.setVisibility(View.VISIBLE);

                        tv = findViewById(R.id.humitidy);
                        tv.setText("The humitidy is " + current);
                        tv.setVisibility(View.VISIBLE);

                        tv = findViewById(R.id.description);
                        tv.setText("The description is " + current);
                        tv.setVisibility(View.VISIBLE);

                        ImageView iv = findViewById(R.id.icon);
                        iv.setImageBitmap(finalImage);
                        iv.setVisibility(View.VISIBLE);
                    });

                }catch(IOException | JSONException ioe){

                    Log.e("Connection error: ", ioe.getMessage());
                }
            });
        });

    }

}