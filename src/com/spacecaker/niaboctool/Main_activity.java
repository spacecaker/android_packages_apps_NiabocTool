package com.spacecaker.niaboctool;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;



public class Main_activity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.spacecaker_main_activity);
        try {
			Runtime.getRuntime().exec("su");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.spacecaker_activity, menu);
        return true;
    }
    
    public void copyStream(String assetFilename, String outFileName ) throws IOException
    { 
      InputStream myInput = getAssets().open(assetFilename); 
      OutputStream myOutput = new FileOutputStream(outFileName);    
      byte[] buffer = new byte[2048];
      int length;
      while ((length = myInput.read(buffer))>0)
      {
        myOutput.write(buffer, 0, length);
      }     
      myOutput.flush();
      myOutput.close();
      myInput.close();    
    }                
    
    public void MessageBox(String message)
    {
    	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }   
          
    
    public void themer() throws IOException, InterruptedException
    {
    	    Process mSuProcess;
    	    mSuProcess = Runtime.getRuntime().exec("su");
    	    new DataOutputStream(mSuProcess.getOutputStream()).writeBytes("mount -o remount rw /system\n");
    	    DataOutputStream mSuDataOutputStream = new DataOutputStream(mSuProcess.getOutputStream());
    	    mSuDataOutputStream.writeBytes("cp /sdcard/build.prop /system/build.prop\n");
    	    Thread.sleep(2000);
            mSuDataOutputStream.writeBytes("chmod 0644 /sdcard/build.prop.apk /system/build.prop\n");
    	    mSuDataOutputStream.writeBytes("exit\n");
    	    	    
    	    MessageBox("Prop Done!");
    	   
    }

    public void themerframework() throws IOException, InterruptedException
    {
        Process mSuProcess;
        mSuProcess = Runtime.getRuntime().exec("su");
        new DataOutputStream(mSuProcess.getOutputStream()).writeBytes("mount -o remount rw /system\n");
        DataOutputStream mSuDataOutputStream = new DataOutputStream(mSuProcess.getOutputStream());
        mSuDataOutputStream.writeBytes("cp /sdcard/framework-res.apk /system/framework/framework-res.apk\n");
        mSuDataOutputStream.writeBytes("chmod 0644 /sdcard/framework-res.apk /system/framework/framework-res.apk\n");
        mSuDataOutputStream.writeBytes("killall com.android.internal.os.ZygoteInit\n");
        mSuDataOutputStream.writeBytes("exit\n");
        MessageBox("Please Reboot Now !");

    }
	
    public void but1(View view) throws IOException, InterruptedException
    {   
    	   copyStream("stock_build.prop","/sdcard/build.prop");
           copyStream("stock_framework.apk","/sdcard/framework-res.apk");
    	   themer();
           themerframework();
    }		
    public void but2(View view) throws IOException, InterruptedException
    {
    	   copyStream("mod1_build.prop","/sdcard/build.prop");
           copyStream("mod1_framework.apk","/sdcard/framework-res.apk");
    	   themer();
           themerframework();
    }
 }
 