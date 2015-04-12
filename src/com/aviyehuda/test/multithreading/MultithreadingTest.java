package com.aviyehuda.test.multithreading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MultithreadingTest extends Activity 
{
    final int SEND=1;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button)findViewById(R.id.Button01)).setOnClickListener(new OnClickListener() 
        {
			@Override
			public void onClick(View v) 
			{
		//		updateUI("start of long operation");
			   // This will cause the UI to freeze
				//updateUI(doLongOperation());
				
				//This causes the application to crash
			/*	(new Thread(new Runnable() {
					
					@Override
					public void run() {
						updateUI(doLongOperation());
					}
				})).start(); */
				
				
				 // option 1
				final Handler myHandler = new Handler()
				{
					@Override
			    	public void handleMessage(Message msg) 
					{
						if(msg.what == SEND){
							updateUI();
						}
						
			    	}
				};
				
				(new Thread(new Runnable() {
					
					@Override
					public void run() {
						
						try
						{
							Message msg = myHandler.obtainMessage();
							for(int i=0; i<10; i++)
							{
								
								Thread.sleep(2000);
								msg.what=SEND;
								myHandler.sendMessage(msg);
								
							}
							

						}
						catch(Exception e)
						{}
						
					}
				})).start();
				
				
				
				// option 2
//				final Handler myHandler = new Handler();
//				
//				
//				(new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						final String res = doLongOperation();
//						
//					//	for (int i = 0; i < 30; i++) {
//							
//							myHandler.post(new Runnable() {
//								
//								@Override
//								public void run() {
//									updateUI(res);
//								}
//							});
//						
//						
//						
//					//	}
//					}
//				})).start();
				
			}
			
		});
    }
    
    int x=1;
    public void updateUI()
    {
    	((TextView)findViewById(R.id.TextView01)).setText("Hi = " + x);
    	x++;
    }
    
    public String doLongOperation() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "end of long operation";
	}
    
    
 
   
}