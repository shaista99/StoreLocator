package com.mapbox.storefinder;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mapbox.storelocator.R;

public class DisplayMessageActivity2 extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static TextView tv2;
    static Dialog d2 ;
    int radio2Id = 0;
    int Floor2 = 6;
    public int Loc_id;
    public String FlrStr;
    RadioGroup radioGroup;
    RadioButton  radioButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message2);
        tv2 = (TextView) findViewById(R.id.selectedFloorLoc2);
        Button b2 = (Button) findViewById(R.id.chooseFloorLoc2);
        b2.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                show();
            }
        });

        radioGroup = findViewById(R.id.radio2);
        textView = findViewById(R.id.selectedVehLoc2);

        Intent intent = getIntent();
        Loc_id = intent.getIntExtra("loc_id",9);
        String Loc_idstr = String.valueOf(Loc_id);
        Toast.makeText(DisplayMessageActivity2.this, "Location is " + Loc_idstr, Toast.LENGTH_SHORT).show();

    }
    public void checkButton2(View v){

        radio2Id = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radio2Id);
        textView.setText("Selected Vehicle: "+ radioButton.getText());
    }

    public void checkout1(View view) {

        if(radio2Id!=0 && Floor2<=4)
        {
            Intent intent = new Intent(this, Checkout1.class);
            intent.putExtra("loc_id",Loc_id+0);
            intent.putExtra("floor_id",FlrStr);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(DisplayMessageActivity2.this, "Please Select Parking Floor and Vehicle Type both", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onValueChange(NumberPicker picker2, int oldVal2, int newVal2) {
        Log.i("value is",""+newVal2);

    }

    public void show()
    {

        final Dialog d2 = new Dialog(DisplayMessageActivity2.this);
        d2.setTitle("NumberPicker");
        d2.setContentView(R.layout.dialog2);
        Button b21 = (Button) d2.findViewById(R.id.button21);
        Button b22 = (Button) d2.findViewById(R.id.button22);
        final NumberPicker np2 = (NumberPicker) d2.findViewById(R.id.numberPicker2);
        np2.setMaxValue(3);
        np2.setMinValue(0);
        np2.setDisplayedValues( new String[] { "Ground Floor", "1st Floor", "2nd Floor","3rd Floor" } );
        np2.setWrapSelectorWheel(false);
        np2.setOnValueChangedListener(this);
        b21.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                tv2.setText("Selected Floor: "+String.valueOf(np2.getValue()));
                d2.dismiss();
                Floor2 = np2.getValue();
                FlrStr = String.valueOf(np2.getValue());
            }
        });

        b22.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d2.dismiss();
            }
        });
        d2.show();


    }
}
