package dev.university.eoapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.activities.category_offers;
import dev.university.eoapp.activities.event_details;
import dev.university.eoapp.models.Event;

public class myeventsAdapter extends RecyclerView.Adapter<myeventsAdapter.eventsViewHolder> {

    private Context mCtx;
    private List<Event> events;

    public myeventsAdapter(Context mCtx, List<Event> events) {
        this.mCtx = mCtx;
        this.events = events;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyler_myvent, parent, false);





        return new eventsViewHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull eventsViewHolder holder, int position) {
        final Event event = events.get(position);

    //    holder.textViewName.setText(event.getName());
        if(event.getName().isEmpty()){
            holder.eventName.setText(event.getPartyLocation());
        }else{
            holder.eventName.setText(event.getName());
        }
//        holder.eventName.setText(event.getNumberOfGuest());

        holder.eventName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                //  new category_offers(request.getId());


                mCtx.startActivity( new Intent( mCtx.getApplicationContext(),event_details.class).putExtra("event_id",  event.getId()));


                          }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class eventsViewHolder extends RecyclerView.ViewHolder {

        Button eventName;
        ImageView seen,notseen;
        LinearLayout request;

        public eventsViewHolder(View itemView) {
            super(itemView);

//            textViewName = itemView.findViewById(R.id.textViewtitle);
//            textcreated_at = itemView.findViewById(R.id.textViewdate);
//
            request = itemView.findViewById(R.id.request);
            eventName = itemView.findViewById(R.id.textViewName);

        }
    }
}
