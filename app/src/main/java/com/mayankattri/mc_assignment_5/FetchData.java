package com.mayankattri.mc_assignment_5;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mayank on 11/11/16.
 */

public class FetchData extends AsyncTask<String, Void, String> {

    private final String LOG_TAG = FetchData.class.getSimpleName();

    public String getData() {
        Document doc;
        String str = "";

        try {
            doc = Jsoup.connect("http://iiitd.ac.in/about").get();

            String title = doc.title();
            str += title;
            System.out.println("title : " + title);

            Elements links = doc.select("p");
            for (Element link : links) {
                str += "\n";
                if (link.text().length() > 20) {
                    str += "\n";
                    str += link.text();
                }
                else {
                    str += link.text();
                }
                System.out.println("text : " + link.text());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return str;
    }

    @Override
    protected String doInBackground(String... strings) {

        return getData();

//        HttpURLConnection urlConnection = null;
//        BufferedReader reader = null;
//        String dataString = "";
//        String URL_string = "https://iiitd.ac.in/about";
//
//        try {
//            URL url = new URL(URL_string);
//            Log.v(LOG_TAG, "Url : " + url);
//
//            urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.connect();
//
//            // Read the input stream into a String
//            InputStream inputStream = urlConnection.getInputStream();
//            StringBuffer buffer = new StringBuffer();
//            if (inputStream == null) {
//                // Nothing to do.
//                dataString = null;
//            }
//            reader = new BufferedReader(new InputStreamReader(inputStream));
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                buffer.append(line + "\n");
//            }
//
//            if (buffer.length() == 0) {
//                // Stream was empty.  No point in parsing.
//                dataString = null;
//            }
//
//            dataString = buffer.toString();
//            Log.v(LOG_TAG,"Data String: "+ dataString);
//        }
//        catch (IOException e) {
//            Log.e(LOG_TAG, "Error ", e);
//            dataString = null;
//        } finally{
//            if (urlConnection != null) {
//                urlConnection.disconnect();
//            }
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (final IOException e) {
//                    Log.e(LOG_TAG, "Error closing stream", e);
//                }
//            }
//        }
    }

    @Override
    protected void onPostExecute(String result) {
        MainActivity.tv.setText(result);
    }
}
