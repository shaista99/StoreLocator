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

public class DisplayMessageActivity3 extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static TextView tv3;
    static Dialog d3;
    int radio3Id = 0;
    int Floor3 = 6;
    public int Loc_id;
    public String FlrStr;
    RadioGroup radioGroup;
    RadioButton  radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message3);
        tv3 = (TextView) findViewById(R.id.selectedFloorLoc3);
        Button b3 = (Button) findViewById(R.id.chooseFloorLoc3);
        b3.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                show();
            }
        });

        radioGroup = findViewById(R.id.radio3);
        textView = findViewById(R.id.selectedVehLoc3);

        Intent intent = getIntent();
        Loc_id = intent.getIntExtra("loc_id",9);
        String Loc_idstr = String.valueOf(Loc_id);
        Toast.makeText(DisplayMessageActivity3.this, "Location is " + Loc_idstr, Toast.LENGTH_SHORT).show();
    }

    public void checkButton3(View v){

        radio3Id = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radio3Id);
        textView.setText("Selected Vehicle: "+ radioButton.getText());
    }

    @Override
    public void onValueChange(NumberPicker picker3, int oldVal3, int newVal3) {
        Log.i("value is",""+newVal3);

    }

    public void checkout1(View view) {

        if(radio3Id!=0 && Floor3<=4)
        {
            Intent intent = new Intent(this, Checkout1.class);
            intent.putExtra("loc_id",Loc_id+0);
            intent.putExtra("floor_id",FlrStr);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(DisplayMessageActivity3.this, "Please Select Parking Floor and Vehicle Type both", Toast.LENGTH_LONG).show();
        }
    }


    public void show()
    {

        final Dialog d3 = new Dialog(DisplayMessageActivity3.this);
        d3.setTitle("NumberPicker");
        d3.setContentView(R.layout.dialog3);
        Button b31 = (Button) d3.findViewById(R.id.button31);
        Button b32 = (Button) d3.findViewById(R.id.button32);
        final NumberPicker np3 = (NumberPicker) d3.findViewById(R.id.numberPicker3);
        np3.setMaxValue(3);
        np3.setMinValue(0);
        np3.setDisplayedValues( new String[] { "Ground Floor", "1st Floor", "2nd Floor","3rd Floor" } );
        np3.setWrapSelectorWheel(false);
        np3.setOnValueChangedListener(this);
        b31.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                tv3.setText("Selected Floor: "+String.valueOf(np3.getValue()));
                d3.dismiss();
                Floor3 = np3.getValue();
                FlrStr = String.valueOf(np3.getValue());

            }
        });

        b32.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d3.dismiss();
            }
        });
        d3.show();


    }
}
