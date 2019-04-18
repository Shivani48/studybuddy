package com.example.studdybuddy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URLEncoder;

public class Login extends AsyncTask<String, Void, String[]> {
        Context context1;
        AlertDialog alertDialog;
        Login (Context ctx) {
        context1 = ctx;
        }
@Override
protected String[] doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://149.125.43.158:1234/admin_code/login.php";
        if(type.equals("login")) {
        try {
        String user_name = params[1];
        String password = params[2];
        URL url = new URL(login_url);
        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
        bufferedWriter.write(post_data);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
        String res="";
        String[] result = new String[2];
        String line="";
        while((line = bufferedReader.readLine())!= null) {
        res += line;
        }
        bufferedReader.close();
        inputStream.close();
        httpURLConnection.disconnect();
        result[0]=res;
        result[1]=user_name;
        return result;
        } catch (MalformedURLException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        return null;
        }

@Override
protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context1).create();
        alertDialog.setTitle("Login Status");
        }

@Override
protected void onPostExecute(String[] result) {
        if (result[0].equals("success")) {
            Intent i = new Intent(context1, Homepage2.class);
            i.putExtra("user",result[1]);
            context1.startActivity(i);
            alertDialog.dismiss();
        } else {
                alertDialog.setMessage(result[0]);
                alertDialog.show();
        }
}
@Override
protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        }
}
