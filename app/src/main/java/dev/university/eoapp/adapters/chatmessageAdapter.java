package dev.university.eoapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
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
import dev.university.eoapp.models.Event;
import dev.university.eoapp.models.Message;

public class chatmessageAdapter extends RecyclerView.Adapter<chatmessageAdapter.eventsViewHolder> {

    private Context mCtx;
    private List<Message> events;

    public chatmessageAdapter(Context mCtx, List<Message> events) {
        this.mCtx = mCtx;
        this.events = events;
    }

    @NonNull
    @Override
    public eventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_chat, parent, false);
        return new eventsViewHolder(view);


    }



    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull eventsViewHolder holder, int position) {
        final Message event = events.get(position);


        if (event.getIsClient().matches("yes")){
            holder.request.setBackgroundColor(R.color.colorFormBackground);

        }else
        {
            holder.textViewName.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            holder.textcreated_at.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        holder.textViewName.setText(event.getMessage());
        holder.textcreated_at.setText(event.getCreatedAt());

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {











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
