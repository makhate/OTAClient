package com.macate.recoverysystemota;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


public class MainActivity extends Activity {

    Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        addListenerOnButton();

    }

    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.button1);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                File src = new File("/storage/sdcard0/Download/ota_signed.zip");
                File dst = new File(Environment.getDownloadCacheDirectory() + "/update.zip");
                FileChannel inChannel = null;
                FileChannel outChannel = null;

                try {
                    inChannel = new FileInputStream(src).getChannel();
                    outChannel = new FileOutputStream(dst).getChannel();
                    inChannel.transferTo(0, inChannel.size(), outChannel);
                } catch (Exception e) {
                    Log.e(e.getMessage(), dst.getAbsolutePath());
                } finally {
                    try {
                        if (inChannel != null)
                            inChannel.close();
                        if (outChannel != null)
                            outChannel.close();
                    } catch (Exception e) {
                        Log.e("Close Error", "Can't Close");
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
