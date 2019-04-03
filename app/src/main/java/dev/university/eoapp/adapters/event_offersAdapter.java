package dev.university.eoapp.adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.activities.event_offersActivity;
import dev.university.eoapp.api.RetrofitClient;
import dev.university.eoapp.models.LoginResponse;
import dev.university.eoapp.models.Offer;
import dev.university.eoapp.models.offers_response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class event_offersAdapter extends RecyclerView.Adapter<event_offersAdapter.UsersViewHolder> {

    private Context mCtx;
    private List<Offer> myrequests;

    String  event_id;

    public event_offersAdapter(Context mCtx, List<Offer> myrequests,String event_id) {
        this.mCtx = mCtx;
        this.myrequests = myrequests;
        this.event_id=event_id;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_event_offers, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        final Offer request = myrequests.get(position);

        holder.textViewName.setText(request.getTitle());
        holder.textViewEmail.setText(request.getComments());

        holder.buttonaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                LayoutInflater li = LayoutInflater.from(mCtx);
                View promptsView = li.inflate(R.layout.pop_up_accept, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setView(promptsView);

                final EditText message = (EditText) promptsView.findViewById(R.id.editTextmessage);


                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Send",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {


                                         sendaccept(message.getText().toString(),request.getId(),event_id );


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

    private void sendaccept(String message, String offer_id, String event_id) {

        final ProgressDialog progressDialog = new ProgressDialog(mCtx);
        progressDialog.setMessage("Loading ..");
        progressDialog.show();
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().acceptoffer(event_id,offer_id,message);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();


                Toast.makeText(mCtx, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public int getItemCount() {
        return myrequests.size();
    }

    class UsersViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewEmail, textViewSchool;

        LinearLayout request;
        Button buttonaccept;


        public UsersViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewtitle);
            textViewEmail = itemView.findViewById(R.id.textViewdate);
            request = itemView.findViewById(R.id.request);
            buttonaccept = itemView.findViewById(R.id.buttonaccept);

        }
    }
}
