package com.example.aleksandar.application;

import android.content.Context;
import android.util.Log;

import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by aleksandar on 5/18/2016.
 */
public class GetRequest {
    String response_string = "";

    public void sendRequest(Context context, String email, String password) {

        HttpClient client = new DefaultHttpClient();
        try {
            HttpGet GET = new HttpGet(
                    "http://dev.mobiletv.bg/4P1/kidsvod/json.php?user=veroun1@gmail.com&pass=test1&mode=categories");

            GET.addHeader(BasicScheme.authenticate(
                    new UsernamePasswordCredentials(email, password),
                    "UTF-8", false));

            Log.v("TAG", "Check if it works !!!");
            HttpResponse response = client.execute(GET);
            Log.v("TAG", response.toString());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                response_string = response_string + line;
            }

        } catch (Exception e) {
            Log.v("TAG", "Something went wrong!");
        }


        client.getConnectionManager().shutdown();

    }


    public String getResponse_string(){
        return response_string;
    }
}