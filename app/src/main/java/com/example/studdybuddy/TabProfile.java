package com.example.studdybuddy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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



public class TabProfile extends Fragment implements NetResponse {
    NetTask netTask;
    TabProfile handle;
    TextView uni;
    TextView mjr;
    TextView phn;
    TextView lvl;
    TextView email;
    TextView name;
    Button logout;
    String university="",major="",phone="",level="",mail="",nam="";
    private Handler mHandler = new Handler(Looper.getMainLooper());
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile, container, false);
        String user=getArguments().getString("user");
        //String user="hinu";
        System.out.println("user="+user);
        name=rootView.findViewById(R.id.name);
        uni = rootView.findViewById(R.id.uni);
        mjr = rootView.findViewById(R.id.mjr);
        phn = rootView.findViewById(R.id.phn);
        lvl = rootView.findViewById(R.id.lvl);
        email = rootView.findViewById(R.id.mail);
        logout=rootView.findViewById(R.id.logout);
        handle = this;

        String request = "email=" + user;
                netTask = new NetTask("http://149.125.43.158:1234/admin_code/profile.php", request, handle);
                netTask.execute((Void) null);

                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent login = new Intent(getActivity(),MainActivity.class);
                        login.putExtra("user","");
                        startActivity(login);
                    }
                });

        return rootView;
    }
    public void netResult(Integer code, final JSONObject json)
    {
        try{
            university=json.getString("University");
            major=json.getString("Major");
            phone=json.getString("Phone");
            level=json.getString("Level");
            mail=json.getString("Email");
            nam=json.getString("Name");
        }
        catch (JSONException e)
        {
            System.out.println("JSON error");
        }

        mHandler.post(new Runnable() {
            public void run() {
                uni.setText(university);
                mjr.setText(major);
                phn.setText(phone);
                lvl.setText(level);
                email.setText(mail);
                name.setText(nam);
        }
        });
    }

    public static class NetTask extends AsyncTask<Void, Void, Boolean> {
        private final String urlString;
        private final String reqString;
        private NetResponse changeListener;

        NetTask(String url, String request, NetResponse responseListener) {
            urlString = url; reqString = request; changeListener = responseListener;
        }
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                JSONObject json = readJsonFromUrl(reqString);
                if (changeListener != null)
                    changeListener.netResult(0, json);
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

        public JSONObject readJsonFromUrl(String request) throws IOException, JSONException {
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
                String jsonText = readAll(rd);
                JSONObject jobj = new JSONObject(jsonText);
                System.out.println("profile="+jsonText);
                return jobj;
            } finally {
                is.close();
            }


        }
    }

}
