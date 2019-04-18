package com.example.studdybuddy;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class TabRequest extends Fragment implements NetResponse{
    // Intent reportIntent = new Intent(this, explore.class);
    //     explore.setContent(reportIntent);
    ArrayList<String> personNames = new ArrayList<>();
    ArrayList<String> universitynames = new ArrayList<>();
    ArrayList<String> majornames = new ArrayList<>();
    ArrayList<String> levelnames = new ArrayList<>();
    ArrayList<String> emaillist = new ArrayList<>();
    Button accept;
    Button reject;
    TabRequest handle;
    NetTask netTask;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.request, container, false);
        handle = this;
        String user=getArguments().getString("user");
        //String user2="rinny";
        System.out.println("user="+user);
        RecyclerView recyclerView1 = rootView.findViewById(R.id.recyclerView1);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(linearLayoutManager1);
        String req = "email=" + user;
        netTask = new NetTask("http://149.125.43.158:1234/admin_code/checkreq.php", req, handle);
        netTask.execute((Void) null);

        CustomAdapter1 customAdapter1 = new CustomAdapter1(getActivity(), personNames, universitynames, majornames,levelnames,accept,reject,emaillist);
        recyclerView1.setAdapter(customAdapter1);
        recyclerView1.invalidate();
       // customAdapter1.notifyDataSetChanged();
        return rootView;
    }

    public void netResult(Integer code, JSONObject json1) {

        try {

            JSONArray userArray = json1.getJSONArray("users");
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetail = userArray.getJSONObject(i);
                emaillist.add(userDetail.getString("id"));
                personNames.add(userDetail.getString("name"));
                universitynames.add(userDetail.getString("university"));
                majornames.add(userDetail.getString("major"));
                levelnames.add(userDetail.getString("level"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static class NetTask extends AsyncTask<Void, Void, Boolean> {
        private final String urlString;
        private final String reqString;
        private NetResponse changeListener;

        NetTask(String url, String request, NetResponse responseListener) {
            urlString = url; reqString = request; changeListener = responseListener;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                System.out.println(reqString);
                JSONObject json1 = readJsonFromUrl(reqString);
                if (changeListener != null)
                    changeListener.netResult(0, json1);
            } catch (IOException e) {
                if (changeListener != null)
                    changeListener.netResult(1, null);
            } catch (JSONException e) {
                if (changeListener != null)
                    changeListener.netResult(2, null);
            }
            return true;
        }
        private String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        JSONObject readJsonFromUrl(String request) throws IOException, JSONException {
            URL nurl = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) nurl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            OutputStream urlout = connection.getOutputStream();
            urlout.write(request.getBytes());
            urlout.close();
            InputStream is = connection.getInputStream();

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String jsonText1 = readAll(rd);
              //  System.out.println("json is:"+jsonText1);
                String newjson1 = "{\"users\": " + jsonText1+"}";
                System.out.println("requests is:"+newjson1);
                return new JSONObject(newjson1);
            } finally {
                is.close();
            }


        }
    }

}