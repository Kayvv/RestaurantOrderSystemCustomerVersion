package nz.ac.unitec.restaurantordersystem.service;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Kay on 2016/9/3.
 */

public class DishDBManager  {

     public static String getMenu(){
         String result;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://kayvv.cc/getCate.php");
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
            return result;
        }catch (IOException e){
            result = e.toString();
            return result;
        }
        finally {
            urlConnection.disconnect();
        }
    }

    public static String readStream(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}