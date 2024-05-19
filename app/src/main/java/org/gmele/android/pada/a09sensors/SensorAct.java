package org.gmele.android.pada.a09sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class SensorAct extends AppCompatActivity implements View.OnClickListener
{
    TextView TvSensorList;
    Button BtNext;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.sensor_lay);
        TvSensorList = findViewById (R.id.TvSensorList);
        BtNext = findViewById (R.id.BtNext1);
        BtNext.setOnClickListener (this);
        GetSensorList ();
    }

    @Override
    public void onClick (View v)
    {
        Intent intent = new Intent (this, AccelerationAct.class);
        startActivity (intent);
        finish ();
    }

    void GetSensorList ()
    {
        SensorManager SM = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
        List<Sensor> SList = SM.getSensorList (Sensor.TYPE_ALL);
        String Str = "";
        for (Sensor s: SList)
            Str = Str + s.getName () + " *" + s.getStringType () + "*\n\n";
        TvSensorList.setText (Str);
    }
}