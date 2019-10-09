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

public class DisplayMessageActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private static TextView tv;
    static Dialog d ;
    int radio1Id = 0;
    int Floor1 = 6;
    public String FlrStr;
    public int Loc_id;
    RadioGroup radioGroup;
    RadioButton  radioButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        tv = (TextView) findViewById(R.id.selectedFloorLoc1);
        Button b = (Button) findViewById(R.id.chooseFloorLoc1);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show();
            }
        });

        radioGroup = findViewById(R.id.radio1);
        textView = findViewById(R.id.selectedVehLoc1);

        Intent intent = getIntent();
        Loc_id = intent.getIntExtra("loc_id",9);  // Value of Location ID+1 from GeoJSON
        String Loc_idstr = String.valueOf(Loc_id);
        Toast.makeText(DisplayMessageActivity.this, "Location is " + Loc_idstr, Toast.LENGTH_SHORT).show();

    }

    public void checkButton(View v){

        radio1Id = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radio1Id);
        textView.setText("Selected Vehicle: "+ radioButton.getText());
    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        Log.i("value is",""+newVal);

    }
    public void checkout1(View view) {

        if(radio1Id!=0 && Floor1<=4)
        {
            Intent intent = new Intent(this, Checkout1.class);
            intent.putExtra("loc_id",Loc_id+0);
            intent.putExtra("floor_id",FlrStr);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(DisplayMessageActivity.this, "Please Select Parking Floor and Vehicle Type both", Toast.LENGTH_LONG).show();
        }
    }

    public void show()
    {

        final Dialog d = new Dialog(DisplayMessageActivity.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(3);
        np.setMinValue(0);
        np.setDisplayedValues( new String[] { "Ground Floor", "1st Floor", "2nd Floor","3rd Floor" } );
        np.setWrapSelectorWheel(false);
        np.setOnValueChangedListener(this);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                tv.setText("Selected Floor: "+String.valueOf(np.getValue()));
                d.dismiss();
                Floor1 = np.getValue();
                FlrStr = String.valueOf(np.getValue());
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();


    }

}
