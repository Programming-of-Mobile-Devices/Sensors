package org.gmele.android.pada.a09sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GyroscopeAct extends AppCompatActivity implements View.OnClickListener, SensorEventListener
{

    TextView TvXGyro;
    TextView TvYGyro;
    TextView TvZGyro;
    Button BtNext;
    SensorManager SM;
    Sensor Gyro;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.gyroscope_lay);
        TvXGyro = (TextView) findViewById (R.id.TvXGyro);
        TvYGyro = (TextView) findViewById (R.id.TvYGyro);
        TvZGyro = (TextView) findViewById (R.id.TvZGyro);
        BtNext = (Button) findViewById (R.id.BtNext3);
        BtNext.setOnClickListener (this);
        DoStaff ();
    }


    private void DoStaff ()
    {
        SM = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
        Gyro = SM.getDefaultSensor (Sensor.TYPE_GYROSCOPE);
        if (Gyro == null)
        {
            TvXGyro.setText ("No Gyroscope here....");
            return;
        }
        SM.registerListener (this, Gyro, SensorManager.SENSOR_DELAY_NORMAL);



    }

    @Override
    public void onSensorChanged (SensorEvent event)
    {
        if (event.sensor == Gyro)
        {
            TvXGyro.setText ("X Axis: " + Rad2Deg (event.values[0]));
            TvYGyro.setText ("Y Axis: " + Rad2Deg (event.values[1]));
            TvZGyro.setText ("Z Axis: " + Rad2Deg (event.values[2]));
        }

    }

    private float Rad2Deg (float R)
    {
        return 360 * R / (2 * (float) Math.PI);
    }

    @Override
    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {
        System.out.println ("*** In here 3");
    }

    @Override
    public void onClick (View v)
    {
        finish ();
        Intent intent = new Intent (this, TouchAct.class);
        startActivity (intent);
    }
}