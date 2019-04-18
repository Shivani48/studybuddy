package com.example.studdybuddy;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONObject;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements NetResponse {

    private ArrayList<String> personNames;
    private ArrayList<String> universitynames;
    private ArrayList<String> majornames;
    private ArrayList<String> levelnames;
    private ArrayList<String> emailist;
    private Button req;
    private Context context;
    private TabExplore.NetTask netTask;
    private CustomAdapter handle;
    private String user;

    CustomAdapter(Context context, ArrayList<String> personNames, ArrayList<String> universitynames, ArrayList<String> majornames, ArrayList<String> levelnames, Button req, ArrayList<String> emailist,String user) {
        this.context = context;
        this.personNames = personNames;
        this.universitynames = universitynames;
        this.majornames = majornames;
        this.levelnames = levelnames;
        this.req=req;
        handle=this;
        this.emailist=emailist;
        this.user=user;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rawlayout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // set the data in items
        holder.name.setText(personNames.get(position));
        holder.university.setText(universitynames.get(position));
        holder.major.setText(majornames.get(position));
        holder.level.setText(levelnames.get(position));
        holder.req.setText("Request");
        final String emailto=emailist.get(position);
        // implement setOnClickListener event on item view.
        holder.req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.req.setText("Request Sent");
              //  String user="hinu";
                String request= "email=" + user + "&emailto=" + emailto;
                netTask = new TabExplore.NetTask("http://149.125.43.158:1234/admin_code/request.php", request, handle);
                netTask.execute((Void) null);
            }
        });

    }


    @Override
    public int getItemCount() {
        return personNames.size();
    }

    @Override
    public void netResult(Integer code, JSONObject json) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, university, major,level;// init the item view's
        Button req;
        MyViewHolder(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            university = (TextView) itemView.findViewById(R.id.university);
            major = (TextView) itemView.findViewById(R.id.major);
            level = (TextView) itemView.findViewById(R.id.level);
            req=itemView.findViewById(R.id.button);

        }
    }
}
