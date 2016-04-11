package com.example.yassine.mifareultralight;

import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    MifareUltralight mMifareUltra = null;
    Button timbraButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getSupportActionBar().hide();

        String hexString = byteArrayToString(WriteOn.getReverseTime());
        Toast.makeText(this, hexString , Toast.LENGTH_LONG).show();

        mNfcAdapter = NfcAdapter.getDefaultAdapter(this.getApplicationContext());

        if(getIntent().getAction().equals(NfcAdapter.ACTION_TECH_DISCOVERED)){
            Tag tag = (Tag) getIntent().getParcelableExtra("android.nfc.extra.TAG");
            String[] techList = tag.getTechList();

            for(int i = 0; i < techList.length; i++){
                if(techList[i].equals(MifareUltralight.class.getName())){
                    mMifareUltra = MifareUltralight.get(tag);
                }
            }
            if(mMifareUltra != null){
                byte [] timeToWriteOn = WriteOn.getReverseTime();
                try {

                    mMifareUltra.connect();

                    mMifareUltra.writePage(10 , timeToWriteOn);

                    mMifareUltra.writePage(12 , timeToWriteOn);

                }catch (IOException e){}
            }
        }

        timbraButton = (Button) findViewById(R.id.buttonTimbra);

        timbraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMifareUltra != null){
                byte [] timeToWriteOn = WriteOn.getReverseTime();
                try {

                    mMifareUltra.connect();

                    mMifareUltra.writePage(10 , timeToWriteOn);

                    mMifareUltra.writePage(12 , timeToWriteOn);

                }catch (IOException e){}
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

    String byteArrayToString(byte[] byteArray){
        String result = "";
        for(int i = 0; i < byteArray.length; i++){
            result += byteArray[i] + ":";
        }
        return result;
    }
}
