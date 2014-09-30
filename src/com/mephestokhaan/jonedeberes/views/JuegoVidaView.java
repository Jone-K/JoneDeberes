package com.mephestokhaan.jonedeberes.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class JuegoVidaView extends View {

	private static final int CELL_SIZE = 30; 
	private int COLUMNS, ROWS;
    private boolean[][] gridArray;
    
    private boolean isInitialized = false;
    private boolean isPlaying = false;
    private Handler stepperHandler = new Handler();
    private static final int STEP_TIME = 100;
    
    Paint backgroundPaint, cellPaint;
    
    public JuegoVidaView(Context context) {
        super(context);
    }

    public JuegoVidaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public JuegoVidaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
    private void initialize()
    {    	
        COLUMNS = getWidth() / CELL_SIZE;
        ROWS = getHeight() / CELL_SIZE;
        
        gridArray = new boolean[ROWS][COLUMNS];
        
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.BLACK);
        
        cellPaint = new Paint();
        cellPaint.setColor(Color.RED);

    	gridArray[0][0] = true;
    	gridArray[ROWS-1][COLUMNS-1] = true;
    	
    	
    	gridArray[10][(COLUMNS / 2) -1] = true;
        gridArray[11][(COLUMNS / 2) -1] = true;
        gridArray[12][(COLUMNS / 2)] = true;
        gridArray[10][(COLUMNS / 2) +1] = true;
        gridArray[11][(COLUMNS / 2) +1] = true;
    	
    }

	@Override
    protected void onDraw(Canvas canvas) 
    {
		if(!isInitialized)
		{
			isInitialized = true;
			initialize();
		}
		
        for (int h = 0; h < ROWS; h++)
        {
            for (int w = 0; w < COLUMNS; w++)
            {
                    canvas.drawRect(
                        w * CELL_SIZE, 
                        h * CELL_SIZE, 
                        (w * CELL_SIZE) +CELL_SIZE,
                        (h * CELL_SIZE) +CELL_SIZE,
                        gridArray[h][w] ? cellPaint : backgroundPaint);
            }
        }
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int X = (int) event.getX();
	    int Y = (int) event.getY();

	    int eventaction = event.getAction();

	    if(eventaction == MotionEvent.ACTION_DOWN)
	    {
	    	int cellRow =  (int)((Y*1.0f/getHeight()) * ROWS);
	    	int cellColumn = (int)((X*1.0f/getWidth()) * COLUMNS);
	    	
	    	gridArray[cellRow][cellColumn] = !gridArray[cellRow][cellColumn];
	    	
	    	this.invalidate();
	    }
	    
	    return true;
	}
	
	public boolean IsPlaying()
	{
		return isPlaying;
	}

	public void Play(boolean play)
	{
		if(isPlaying == play)
		{
			return;
		}
		
		isPlaying = play;
		if(isPlaying)
		{
			stepperHandler.postDelayed(stepper, STEP_TIME);
		}
	}
	
	private Runnable stepper = new Runnable()
	{
	   @Override
	   public void run()
	   {
		   if(isPlaying)
		   {
			   Step();
			   stepperHandler.postDelayed(this, STEP_TIME);
		   }
	   }
	};
		
	
	public void Step()
	{		
		for(int r=ROWS-1; r>=0; r--)
		{			
			for(int c=COLUMNS-1; c>=0; c--)
			{
				if (r==0)
				{
					gridArray[r][c] = gridArray[(ROWS-1)%ROWS][c];
				}
				else
				{
					gridArray[r][c] = gridArray[(r-1)%ROWS][c];
				}
			}
				
		}
		
		this.invalidate();
	}
	
	
	public void Clear()
	{
		for(int r=0; r < ROWS; r++)
		{
			for(int c=0; c < COLUMNS; c++)
			{
				gridArray[r][c] = false;
			}
		}
		
		this.invalidate();
	}
	
}
