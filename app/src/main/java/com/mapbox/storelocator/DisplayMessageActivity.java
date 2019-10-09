package com.mapbox.storelocator;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.mapbox.storefinder.Checkout1;

public class DisplayMessageActivity extends Activity {


    public void checkout1(View view) {
        Intent intent = new Intent(this, Checkout1.class);
        startActivity(intent);
    }
}
