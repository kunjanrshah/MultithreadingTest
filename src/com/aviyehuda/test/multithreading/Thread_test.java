package com.aviyehuda.test.multithreading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Thread_test extends Activity 
{
	TextView txtUpdate;
	Button btn;
	Message msg;
	int x=1;
	Handler innerHandler;
	public void onCreate(Bundle saveInstance)
	{
		super.onCreate(saveInstance);
		setContentView(R.layout.main);
		txtUpdate=(TextView)findViewById(R.id.TextView01);
		btn=(Button)findViewById(R.id.Button01);
		
		final Handler myhandler=new Handler()
		{
			public void handleMessage(Message msg)
			{
					updateUI();
			}			
		};
		
		msg=myhandler.obtainMessage();
		new Thread(new Runnable() 
		{
			@Override
			public void run() 
			{
				try
				{
					for(int i=0; i<20; i++)
					{
						Thread.sleep(1000);
				
						myhandler.sendMessage(msg);
					}
				}
				catch(Exception e){e.printStackTrace(); System.out.println("kunjan");}
			}
		}).start();

		
//		(new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//
//				Looper.prepare();
//				innerHandler = new Handler();
//						
//				Message message = innerHandler.obtainMessage();
//				innerHandler.dispatchMessage(message);
//				Looper.loop();
//			}
//		})).start();
//
	}
//
//
//	protected void onDestroy() {
//		innerHandler.getLooper().quit();
//		super.onDestroy();
//	}
	
	private void updateUI() 
	{
		txtUpdate.setText("hi"+x);
		x++;
	}
}
