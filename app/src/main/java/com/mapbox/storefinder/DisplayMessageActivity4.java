package com.mapbox.storefinder;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.storelocator.R;

public class DisplayMessageActivity4 extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static TextView tv4;
    static Dialog d4;
    int radio4Id = 0;
    int Floor4 = 6;
    public int Loc_id;
    RadioGroup radioGroup;
    public String FlrStr;
    RadioButton  radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message4);
        tv4 = (TextView) findViewById(R.id.selectedFloorLoc4);
        Button b4 = (Button) findViewById(R.id.chooseFloorLoc4);
        b4.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                show();
            }
        });

        radioGroup = findViewById(R.id.radio4);
        textView = findViewById(R.id.selectedVehLoc4);

        Intent intent = getIntent();
        Loc_id = intent.getIntExtra("loc_id",9);
        String Loc_idstr = String.valueOf(Loc_id);
        Toast.makeText(DisplayMessageActivity4.this, "Location is " + Loc_idstr, Toast.LENGTH_SHORT).show();
    }

    public void checkButton4(View v){

        radio4Id = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radio4Id);
        textView.setText("Selected Vehicle: "+ radioButton.getText());
    }

    @Override
    public void onValueChange(NumberPicker picker4, int oldVal4, int newVal4) {
        Log.i("value is",""+newVal4);

    }

    public void checkout1(View view) {

        if(radio4Id!=0 && Floor4<=3)
        {
            Intent intent = new Intent(this, Checkout1.class);
            intent.putExtra("loc_id",Loc_id+0);
            intent.putExtra("floor_id",FlrStr);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(DisplayMessageActivity4.this, "Please Select Parking Floor and Vehicle Type both", Toast.LENGTH_LONG).show();
        }
    }

    public void show()
    {

        final Dialog d4 = new Dialog(DisplayMessageActivity4.this);
        d4.setTitle("NumberPicker");
        d4.setContentView(R.layout.dialog4);
        Button b41 = (Button) d4.findViewById(R.id.button41);
        Button b42 = (Button) d4.findViewById(R.id.button42);
        final NumberPicker np4 = (NumberPicker) d4.findViewById(R.id.numberPicker4);
        np4.setMaxValue(2);
        np4.setMinValue(0);
        np4.setDisplayedValues( new String[] { "Ground Floor", "1st Floor", "2nd Floor" } );
        np4.setWrapSelectorWheel(false);
        np4.setOnValueChangedListener(this);
        b41.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                tv4.setText("Selected Floor: "+String.valueOf(np4.getValue()));
                d4.dismiss();
                Floor4 = np4.getValue();
                FlrStr = String.valueOf(np4.getValue());
            }
        });

        b42.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d4.dismiss();
            }
        });
        d4.show();


    }
}
