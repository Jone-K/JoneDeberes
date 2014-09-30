package com.mephestokhaan.jonedeberes;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;

public class MenuActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		
	}
	
	public void goToLeyDhont(View v)
	{
		startActivity(new Intent(this, LeyDhontActivity.class));
	}
	public void goToJuegoVida(View v)
	{
		startActivity(new Intent(this, JuegoVidaActivity.class));
	}

}
