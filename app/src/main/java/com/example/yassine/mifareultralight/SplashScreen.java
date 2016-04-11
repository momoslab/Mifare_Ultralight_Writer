package com.example.yassine.mifareultralight;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SplashScreen extends AppCompatActivity {

    private Handler mHandler;
    private static final int SPASH_TIME_OUT = 3*1000;

    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this.getApplicationContext());

        if(!isNFCEnabled()){
            Toast.makeText(this, "Abilita NFC" ,Toast.LENGTH_LONG);
        }
        else{

            mHandler = new Handler();

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    //chiudi questa attività
                    finish();
                }
            }, SPASH_TIME_OUT);

        }
    }

    private boolean isNFCEnabled(){
        return (mNfcAdapter!= null) && (mNfcAdapter.isEnabled());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash_screen, menu);
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
