package dev.university.eoapp.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import dev.university.eoapp.models.Offer;

public class offersAdapter extends RecyclerView.Adapter<offersAdapter.UsersViewHolder> {

    private Context mCtx;
    private List<Offer> myrequests;

    public offersAdapter(Context mCtx, List<Offer> myrequests) {
        this.mCtx = mCtx;
        this.myrequests = myrequests;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.offer_raw, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        final Offer request = myrequests.get(position);

     //  holder.textViewName.setText(request.getTitle());
       holder.created_at.setText(request.getCreatedAt());
       holder.title.setText(request.getTitle());
       holder.comments.setText(request.getComments());

        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                LayoutInflater li = LayoutInflater.from(mCtx);
                View promptsView = li.inflate(R.layout.pop_up, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                final TextView title = (TextView) promptsView.findViewById(R.id.title);
                final TextView provider_id = (TextView) promptsView.findViewById(R.id.provider_id);
                final TextView comments = (TextView) promptsView.findViewById(R.id.comments);


                title.setText(request.getTitle());
                provider_id.setText(request.getProviderId());
                comments.setText(request.getComments());

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        //getting the values

                                        //sending the message

                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

//
                //  mCtx.sendBroadcast(new Intent("start.fragment_myitem.action").putExtra("id", user.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return myrequests.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView title, comments, created_at;
//        ImageView seen,notseen;
       LinearLayout request;

        public UsersViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            comments = itemView.findViewById(R.id.comments);
            created_at = itemView.findViewById(R.id.created_at);
//            seen = itemView.findViewById(R.id.seen);
//            notseen = itemView.findViewById(R.id.notseen);
            request = itemView.findViewById(R.id.request);

        }
    }
}
