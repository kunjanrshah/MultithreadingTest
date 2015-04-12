package com.aviyehuda.test.multithreading;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

public class Test_thread extends Activity
{
	Handler myhandler;
	TextView txtview1;
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		txtview1=(TextView)findViewById(R.id.TextView01);
		
		MyAsyncTask aTask = new MyAsyncTask();
		aTask.execute(1, 2, 3, 4, 5);
		
//		myhandler=new Handler()
//		{
//			public void handleMessage(Message msg)
//			{
//				updateUI();
//			}
//		};
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() 
//			{
//					Message msg=myhandler.obtainMessage();
//					
//					myhandler.sendMessage(msg);
//			}
//		}).start();
		
	}
	
	private  void updateUI()
	{
		System.out.println("hi kunjan");
		Toast.makeText(Test_thread.this, "kunjan", Toast.LENGTH_SHORT).show();
		
	}
	
	public void updateUI(String str)
	{
		txtview1.setText(str);
		System.out.println(str);
		//Toast.makeText(Test_thread.this, "kunjan", Toast.LENGTH_SHORT).show();
	}
	
	class MyAsyncTask extends AsyncTask<Integer, String, Long> 
	{
		@Override
		protected Long doInBackground(Integer... params) 
		{
			long start = System.currentTimeMillis();
			
			for (Integer integer : params) 
			{
				publishProgress("start processing "+integer);
				doLongOperation();
				publishProgress("done processing "+integer);
			}
			
			return start - System.currentTimeMillis();
		}
		
		@Override
		protected void onProgressUpdate(String... values) 
		{
			updateUI(values[0]);
		}
		
		@Override
		protected void onPostExecute(Long time) 
		{
			updateUI("Done with all the operations, it took:" +time + " millisecondes");
		}

		@Override
		protected void onPreExecute()
		{
			updateUI("Starting process");
		}
		
		public void doLongOperation() 
		{
			try 
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
}
