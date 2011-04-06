
package net.impjq.download;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.util.Log;

/**
 * Add some common functions here,such as log.
 * 
 * @author Percy.Peng
 */
public class Utils {
    public final static boolean DEBUG_ENABLE = true;

    /**
     * Print the log message.
     * 
     * @param LOGTAG
     * @param msg
     */
    public static void log(String TAG, String msg) {
        if (DEBUG_ENABLE) {
            Log.i("[pjq]"+TAG, msg);
        }
    }

    public static String getRawData(String string) {
        String dataString="" ;

        try {
            URL url;
            url = new URL(string);
            URLConnection conn = url.openConnection();
            // conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            byte buffer[] = new byte[1024];

            String readerString;
            while ((readerString = reader.readLine()) != null) {
                dataString += readerString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            log("IOException", "" + e);
        }
        log("getRawData", dataString);

        return dataString;
    }
}
