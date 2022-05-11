package com.ismagi.hotelreservation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ismagi.hotelreservation.R;
import com.ismagi.hotelreservation.databinding.ReservationItemBinding;
import com.ismagi.hotelreservation.models.Reservation;
import com.ismagi.hotelreservation.ui.fragments.ListReservationFragment;
import com.ismagi.hotelreservation.ui.fragments.ListReservationFragmentDirections;
import com.ismagi.hotelreservation.ui.fragments.SingleReservationFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private Context mContext;
    private List<Reservation> mReservations;

    public ReservationAdapter(Context mContext) {
        this.mContext = mContext;
        this.mReservations = new ArrayList<>();
    }

    public void setReservations(List<Reservation> reservations) {

        this.mReservations = reservations;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReservationItemBinding itemBinding = ReservationItemBinding.inflate(layoutInflater, parent,false);

        ViewHolder holder = new ViewHolder(itemBinding);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListReservationFragmentDirections.ActionListReservationFragmentToSingleReservationFragment action = ListReservationFragmentDirections.actionListReservationFragmentToSingleReservationFragment(holder.reservationItemBinding.getReservation());
                Navigation.findNavController(view).navigate(action);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.reservationItemBinding.setReservation(mReservations.get(position));

    }

    @Override
    public int getItemCount() {
        return this.mReservations == null ? 0 : this.mReservations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ReservationItemBinding reservationItemBinding;


        public ViewHolder(@NonNull ReservationItemBinding itemView) {
            super(itemView.getRoot());
            reservationItemBinding = itemView;

        }
    }
}
