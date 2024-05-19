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

import java.util.List;

public class AccelerationAct extends AppCompatActivity implements View.OnClickListener, SensorEventListener
{

    TextView TvXAxis;
    TextView TvYAxis;
    TextView TvZAxis;
    TextView TvXGrav;
    TextView TvYGrav;
    TextView TvZGrav;
    Button BtNext;
    SensorManager SM;
    Sensor Acc;
    Sensor Grav;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.acceleration_lay);
        TvXAxis = (TextView) findViewById (R.id.TvXAxis);
        TvYAxis = (TextView) findViewById (R.id.TvYAxis);
        TvZAxis = (TextView) findViewById (R.id.TvZAxis);
        TvXGrav = (TextView) findViewById (R.id.TvXGrav);
        TvYGrav = (TextView) findViewById (R.id.TvYGrav);
        TvZGrav = (TextView) findViewById (R.id.TvZGrav);
        BtNext = (Button) findViewById (R.id.BtNext2);
        BtNext.setOnClickListener (this);
        DoStaff ();
    }

    private void DoStaff ()
    {
        SM = (SensorManager) getSystemService (Context.SENSOR_SERVICE);
        List<Sensor> lst = SM.getSensorList (Sensor.TYPE_ACCELEROMETER);            //Raw Sensor
        System.out.println ("*** Number of Accelerometers : " + lst.size ());
        if (lst.isEmpty ())
        {
            TvXAxis.setText ("No Accelerometer here....");
            return;
        }
        Acc = lst.get (0);
        //Acc = SM.getDefaultSensor (Sensor.TYPE_ACCELEROMETER);
        SM.registerListener (this, Acc, SensorManager.SENSOR_DELAY_NORMAL);

        //List<Sensor> lst1 = SM.getSensorList (Sensor.TYPE_GRAVITY);
        List<Sensor> lst1 = SM.getSensorList (Sensor.TYPE_LINEAR_ACCELERATION);
        System.out.println ("*** Number of Gravity Sensors: " + lst1.size ());
        if (lst1.isEmpty ())
        {
            TvXGrav.setText ("No Gravity sensor here....");
            return;
        }
        Grav = lst1.get (0);
        SM.registerListener (this, Grav, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onClick (View v)
    {
        Intent intent = new Intent (this, GyroscopeAct.class);
        startActivity (intent);
        finish ();

    }

    @Override
    public void onSensorChanged (SensorEvent event)
    {
        if (event.sensor == Acc)
        {
            TvXAxis.setText ("X Axis: " + event.values[0]);
            TvYAxis.setText ("Y Axis: " + event.values[1]);
            TvZAxis.setText ("Z Axis: " + event.values[2]);
        }
        if (event.sensor == Grav)
        {
            TvXGrav.setText ("X Axis Gravity: " + event.values[0]);
            TvYGrav.setText ("Y Axis Gravity: " + event.values[1]);
            TvZGrav.setText ("Z Axis Gravity: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged (Sensor sensor, int accuracy)
    {
        System.out.println ("*** In here 1");
    }


}