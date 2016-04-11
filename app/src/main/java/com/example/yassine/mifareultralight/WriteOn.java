package com.example.yassine.mifareultralight;

/**
 * Created by YassIne on 07/09/2015.
 */
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.nfc.Tag;
import android.nfc.tech.MifareUltralight;
import android.speech.tts.TextToSpeech;
import android.util.Log;

/**
 * Created by momo on 10/12/2014.
 */
public class WriteOn {

    private static Calendar getGttEpochCalendar(){

        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat localSimpleDataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ITALY);

        try {

            localCalendar.setTime(localSimpleDataFormat.parse("01/01/2005 00:00:00"));
        }

        catch (ParseException localParseException) {}

        return localCalendar;


    }

    public static byte[] getReverseTime(){

        /*

        Calendar localCalendar = Calendar.getInstance();
        SimpleDateFormat localSimpleDataFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.ITALY);

        Date data= Calendar.getInstance().getTime();

        long epoch=0;

        try {

            localCalendar.setTime(localSimpleDataFormat.parse("01/01/2005 00:00"));
            // epoch = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.ITALY).parse("01/01/2005 00:00").getTime();
        }

        catch (ParseException localParseException) {}
        */

        Calendar gttEpoch = getGttEpochCalendar();

        System.out.println("calendario gttEpoch in mills" + gttEpoch.getTimeInMillis());

        Calendar localCalendar = Calendar.getInstance();

        long gttEpochTimeInMinutes = gttEpoch.getTimeInMillis() / 60000L;

        long l2 = (localCalendar.getTimeInMillis() / 60000L) - gttEpochTimeInMinutes;

        //l2 is the key
        int shiftedTime = (int) l2 << 8;

        Log.i("WriteOn", "ShifetdTime --> " + shiftedTime + " Long 2 --> " + l2);

        String hexTimeShifted = Integer.toHexString(shiftedTime);

        byte[] bytes = ByteBuffer.allocate(4).putInt(shiftedTime).array();

        System.out.println("Shifted time byte array --> " + byteArrayToString(bytes));
        System.out.println("Hax shifted time --> " + hexTimeShifted);

        return bytes;

    }
    public static byte[] longToBytes(int x) {
        String hex = Integer.toHexString(x);

        return StringToByteArray(hex);
    }

    public static byte[] StringToByteArray(String hex)
    {
        byte[] b = new BigInteger(hex,16).toByteArray();
        return b;
    }

    static String byteArrayToString(byte[] byteArray){
        String result = "";
        for(int i = 0; i < byteArray.length; i++){
            result += byteArray[i] + ":";
        }
        return result;
    }
}