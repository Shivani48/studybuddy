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


public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.MyViewHolder1> implements NetResponse {

    private ArrayList<String> personNames;
    private ArrayList<String> universitynames;
    private ArrayList<String> majornames;
    private ArrayList<String> levelnames;
    private ArrayList<String> emailist;
   private Button acpt;
   private Button rjt;
    private Context context;
  //  TabRequest.NetTask netTask;
  private CustomAdapter1 handle;

    CustomAdapter1(Context context, ArrayList<String> personNames, ArrayList<String> universitynames, ArrayList<String> majornames, ArrayList<String> levelnames, Button acpt, Button rjt, ArrayList<String> emailist) {
        this.context = context;
        this.personNames = personNames;
        this.universitynames = universitynames;
        this.majornames = majornames;
        this.levelnames = levelnames;
        this.acpt=acpt;
        this.rjt=rjt;
        handle=this;
        this.emailist=emailist;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.requestlayout, parent, false);
        MyViewHolder1 vh = new MyViewHolder1(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder1 holder, final int position) {

        // set the data in items
        holder.name.setText(personNames.get(position));
        holder.university.setText(universitynames.get(position));
        holder.major.setText(majornames.get(position));
        holder.level.setText(levelnames.get(position));
        holder.acpt.setText("Accept");
        holder.rjt.setText("Reject");
        final String emailto=emailist.get(position);
        // implement setOnClickListener event on item view.
        holder.acpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.acpt.setText("Accepted");
               /* String user="hinu";
                String request= new StringBuilder().append("email=").append(user).append("&emailto=").append(emailto).toString();
                netTask = new TabExplore.NetTask("http://192.168.1.10:1234/admin_code/request.php", request, handle);
                netTask.execute((Void) null);*/
            }
        });
        holder.rjt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.rjt.setText("Rejected");
               /* String user="hinu";
                String request= new StringBuilder().append("email=").append(user).append("&emailto=").append(emailto).toString();
                netTask = new TabExplore.NetTask("http://192.168.1.10:1234/admin_code/request.php", request, handle);
                netTask.execute((Void) null);*/
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

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        TextView name, university, major,level;// init the item view's
        Button rjt,acpt;
        MyViewHolder1(View itemView) {
            super(itemView);

            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.name);
            university = (TextView) itemView.findViewById(R.id.university);
            major = (TextView) itemView.findViewById(R.id.major);
            level = (TextView) itemView.findViewById(R.id.level);
            rjt=itemView.findViewById(R.id.reject);
            acpt=itemView.findViewById(R.id.accept);
    }
    }
}
