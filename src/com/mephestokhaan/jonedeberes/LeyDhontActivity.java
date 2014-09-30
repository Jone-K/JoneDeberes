package com.mephestokhaan.jonedeberes;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LeyDhontActivity extends Activity {

	final int escanos = 365;
	final int [] votos = {350000, 200000, 100000, 20000, 700, 400};
	
	TextView datosView;
	TextView resultadoJoneView;
	TextView resultadoLucaView;
	
	LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leydhont);
		
		layout = (LinearLayout) findViewById(R.id.root_layout);
		
		datosView = (TextView) findViewById(R.id.textViewDatos);
		resultadoJoneView = (TextView) findViewById(R.id.textViewJoneRes);
		resultadoLucaView = (TextView) findViewById(R.id.textViewLucaRes);
		
		PrintArrayToTextView(votos, datosView);
	}
	
	public void UpdateResults(View v)
	{
		int[] resultJone = CalculateJoneResult();
		int[] resultLuca = CalculateLucaResult();
		
		PrintArrayToTextView(resultJone, resultadoJoneView);
		PrintArrayToTextView(resultLuca, resultadoLucaView);
		
		if(CompareResults(resultJone, resultLuca))
		{
			layout.setBackgroundColor(Color.GREEN);
		}else{
			layout.setBackgroundColor(Color.RED);
		}
		
		
	}
	
	private int[] CalculateJoneResult()
	{
		int [] resultados = new int[votos.length];
		
		int m = 0;
		
		for(int n = 0 ; n < escanos ; n++)
		{
			int mayorvalor = 0;
			for(int i = 0 ; i < votos.length ; i++)
			{
				int temp = votos[i]/(resultados[i] + 1);
				if(temp > mayorvalor)
				{
					mayorvalor = temp;
					m = i;
				}
			}	
			resultados[m] = resultados[m]+1;
		}
		return resultados;
	}
	
	
	private void PrintArrayToTextView(int [] array, TextView display)
	{
		String text = ""; 
		for(int i = 0 ; i < array.length; i++)
		{
			text = text + array[i] + " | ";
		}
		display.setText(text);
	}
	
	
	private boolean CompareResults(int [] resultA, int [] resultB)
	{
		if(resultA.length != resultB.length)
		{
			return false;
		}
		
		for(int i = 0; i < resultA.length; i++)
		{
			if(resultA[i] != resultB[i])
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	
	private int [] CalculateLucaResult()
	{
		if(votos.length == 0)
		{
			return new int[]{0};
		}
		
		int [] resultados = new int[votos.length];
		
		for(int i = 0; i < escanos; i++)
		{
			float max_val = 0;
			int max_index = 0;
			for(int j = 0; j < votos.length; j++)
			{
				float val = votos[j]*1.0f/(resultados[j] + 1.0f);
				if(val > max_val)
				{
					max_val = val;
					max_index = j;
				}
			}
			resultados[max_index]++;
		}
		
		return resultados;
	}
}
