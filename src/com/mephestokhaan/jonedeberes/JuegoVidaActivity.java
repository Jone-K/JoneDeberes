package com.mephestokhaan.jonedeberes;

import com.mephestokhaan.jonedeberes.views.JuegoVidaView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class JuegoVidaActivity extends Activity
{

	private JuegoVidaView juegoView;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_juegovida);
        
        juegoView = (JuegoVidaView) findViewById(R.id.juegoVidaView1);
    }
    
    public void Play(View v)
    {
    	Button button = (Button) v;
    	boolean play = !juegoView.IsPlaying();
    	juegoView.Play(play);
    	button.setText(getResources().getString(play? R.string.control_stop : R.string.control_play));
    }

    public void Step(View v)
    {
    	juegoView.Step();
    }
    public void Clear(View v)
    {
    	juegoView.Clear();
    }
    
    
}
