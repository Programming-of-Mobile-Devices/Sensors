package org.gmele.android.pada.a09sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TouchAct extends AppCompatActivity implements View.OnTouchListener
{
    Button[] Buttons;
    RelativeLayout RlScreen;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate (savedInstanceState);
        Buttons = new Button[10];
        for (int i = 0; i < 10; i++)
            Buttons[i] = null;
        setContentView (R.layout.touch_lay);
        RlScreen = findViewById (R.id.RlScreen);
        RlScreen.setOnTouchListener (this);
    }

    public boolean onTouch (View v, MotionEvent event)
    {
        int Index = event.getActionIndex ();
        int ID = event.getPointerId (Index);
        if (event.getActionMasked () != 2)                                     //Movement
            System.out.println (" ---- " + Index + "  -  " + ID + " - " + event.getActionMasked ());
        switch (event.getActionMasked ())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                NewTouch (ID, event.getX (Index), event.getY (Index));
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                RemoveTouch (ID);
                break;
            case MotionEvent.ACTION_MOVE:
                MoveTouch (ID, event.getX (Index), event.getY (Index));
                break;
        }
        return true;
    }

    private void NewTouch (int ID, float X, float Y)
    {
        if (Buttons[ID] != null)
        {
            System.out.println ("*** What??? ***");
            return;
        }
        Buttons[ID] = new Button (this);
        Buttons[ID].setText ("" + ID);
        Buttons[ID].setX ((int) X);
        Buttons[ID].setY ((int) Y);
        RlScreen.addView (Buttons[ID]);
    }

    private void RemoveTouch (int ID)
    {
        if (Buttons[ID] == null)
        {
            System.out.println ("*** What???? ***");
            return;
        }
        RlScreen.removeView (Buttons[ID]);
        Buttons[ID] = null;
    }

    private void MoveTouch (int ID, float X, float Y)
    {
        if (Buttons[ID] == null)
        {
            System.out.println ("*** What????? ***");
            return;
        }
        Buttons[ID].setX ((int) X);
        Buttons[ID].setY ((int) Y);
    }
}