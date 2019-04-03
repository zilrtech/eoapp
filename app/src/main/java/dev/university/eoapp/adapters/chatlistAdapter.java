package dev.university.eoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.activities.ChatlistActivity;
import dev.university.eoapp.activities.event_details;
import dev.university.eoapp.models.Event;

public class chatlistAdapter extends RecyclerView.Adapter<chatlistAdapter.eventsViewHolder> {

    private Context mCtx;
    private List<Event> events;

    public chatlistAdapter(Context mCtx, List<Event> events) {
        this.mCtx = mCtx;
        this.events = events;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.dialog_response, parent, false);
        return new eventsViewHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull eventsViewHolder holder, int position) {
        final Event event = events.get(position);
        //holder.textViewName.setText("SSSS");

        if(event.getName().isEmpty()){
           holder.textViewName.setText(event.getPartyLocation());

        }else{
          holder.textViewName.setText(event.getName());

        }

        holder.textcreated_at.setText(event.getCreatedAt());

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                mCtx.startActivity( new Intent( mCtx.getApplicationContext(),ChatlistActivity.class).putExtra("event_id",  event.getId()));






            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class eventsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textcreated_at, textViewSchool;
        ImageView seen,notseen;
        LinearLayout request;

        public eventsViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewtitle);
            textcreated_at = itemView.findViewById(R.id.textViewdate);

            request = itemView.findViewById(R.id.request);

        }
    }
}
