package com.mapbox.storefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.storelocator.R;
import com.mapbox.storelocator.activity.MapActivity;

import java.util.Random;


public class Checkout1 extends AppCompatActivity {
    Random Number;
    public int Loc_id;
    public String Floor_id;
    TextView RandomNumber;
    int Rnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout1);
        RandomNumber = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        Loc_id = intent.getIntExtra("loc_id",9);  // Value of Location ID+1 from GeoJSON
        String Loc_idstr = String.valueOf(Loc_id);
        Floor_id = intent.getStringExtra("floor_id");

        Toast.makeText(Checkout1.this, "Location is " + Loc_idstr + " Floor is " + Floor_id, Toast.LENGTH_SHORT).show();

        Number = new Random();
        Rnumber = Number.nextInt(1000);
        RandomNumber.setText(Integer.toString(Rnumber));
    }

    public void backtohome(View v){

        Intent intent = new Intent(this, MapActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
