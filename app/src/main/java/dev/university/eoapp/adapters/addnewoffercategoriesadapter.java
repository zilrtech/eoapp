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

import java.util.List;

import dev.university.eoapp.R;
import dev.university.eoapp.activities.AddNewOffer;
import dev.university.eoapp.activities.category_offers;
import dev.university.eoapp.models.Category;

public class addnewoffercategoriesadapter extends RecyclerView.Adapter<addnewoffercategoriesadapter.categoriesViewHolder> {

    private Context mCtx;
    private List<Category> categories;

    public addnewoffercategoriesadapter(Context mCtx, List<Category> categories) {
        this.mCtx = mCtx;
        this.categories = categories;
    }

    @NonNull
    @Override
    public categoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_categories, parent, false);
        return new categoriesViewHolder(view);


    }



    @Override
    public void onBindViewHolder(@NonNull categoriesViewHolder holder, int position) {
        final Category request = categories.get(position);

        holder.textViewName.setText(request.getName());
        holder.textcreated_at.setText(request.getCreatedAt());

        holder.imageV.setImageResource(R.drawable.kids);

        if(request.getName().matches("Kids")){
            holder.imageV.setImageResource(R.drawable.kids);
        }else if(request.getName().matches("Wedding")){
            holder.imageV.setImageResource(R.drawable.wedding);
        }

        else if(request.getName().matches("Birthday") ) {
            holder.imageV.setImageResource(R.drawable.birthday);
        }

        else if(request.getName().matches("Graduation") ){
            holder.imageV.setImageResource(R.drawable.graduation);
        }

        else if(request.getName().matches("Other")){
            holder.imageV.setImageResource(R.drawable.other);
        }


        holder.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                //  new category_offers(request.getId());


                mCtx.startActivity( new Intent( mCtx.getApplicationContext(), AddNewOffer.class).putExtra("cat_id",request.getId()));


            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class categoriesViewHolder extends RecyclerView.ViewHolder {

        TextView  textcreated_at, textViewSchool;
        ImageView imageV;
        ImageView seen,notseen;
        Button textViewName;
        LinearLayout request;

        public categoriesViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textcreated_at = itemView.findViewById(R.id.textViewdate);
            imageV = itemView.findViewById(R.id.imageV);

            request = itemView.findViewById(R.id.request);

        }
    }
}
