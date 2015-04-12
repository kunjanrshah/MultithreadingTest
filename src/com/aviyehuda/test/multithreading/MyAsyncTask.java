package com.aviyehuda.test.multithreading;

import android.os.AsyncTask;

class MyAsyncTask extends AsyncTask<Integer, String, Long> {
	
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
	protected void onProgressUpdate(String... values) {
		new Test_thread().updateUI(values[0]);
	}
	
	@Override
	protected void onPostExecute(Long time) {
		new Test_thread().updateUI("Done with all the operations, it took:" +time + " millisecondes");
	}

	@Override
	protected void onPreExecute() {
		new Test_thread().updateUI("Starting process");
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
