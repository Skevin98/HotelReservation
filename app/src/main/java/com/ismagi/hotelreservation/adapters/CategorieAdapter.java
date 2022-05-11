package com.ismagi.hotelreservation.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ismagi.hotelreservation.CreateReservationActivity;
import com.ismagi.hotelreservation.MenuActivity;
import com.ismagi.hotelreservation.models.Categorie;
import com.ismagi.hotelreservation.databinding.CategorieItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategorieAdapter extends RecyclerView.Adapter<CategorieAdapter.ViewHolder> {

    private Context mContext;
    private List<Categorie> mCategories;

    public CategorieAdapter(Context mContext) {
        this.mContext = mContext;
        this.mCategories = new ArrayList<>();

    }

    public void setCategories(List<Categorie> categories) {

        this.mCategories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CategorieItemBinding itemBinding = CategorieItemBinding.inflate(layoutInflater, parent,false);

        ViewHolder holder = new ViewHolder(itemBinding);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createReservation = new Intent(mContext, CreateReservationActivity.class);
                createReservation.putExtra("idCategorie", holder.categorieItemBinding.getCategorie().getIdCategorie());
                Log.i("CategorieAdapter", "onClick: "+holder.categorieItemBinding.getCategorie().getIdCategorie());
                mContext.startActivity(createReservation);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categorieItemBinding.setCategorie(mCategories.get(position));

        //Log.i("Adapter", "onBindViewHolder: "+holder.categorieItemBinding.getCategorie().getLibelle());
    }

    @Override
    public int getItemCount() {
        return this.mCategories == null ? 0 : this.mCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CategorieItemBinding categorieItemBinding;


        public ViewHolder(@NonNull CategorieItemBinding itemView) {
            super(itemView.getRoot());
            categorieItemBinding = itemView;

        }
    }
}
