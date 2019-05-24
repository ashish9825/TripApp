package com.ashishpanjwani.tripapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ashishpanjwani.tripapp.Model.Vehicle;
import com.ashishpanjwani.tripapp.R;

import java.util.ArrayList;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> implements Filterable {
    private ArrayList<Vehicle> mArrayList;
    private ArrayList<Vehicle> mFilteredList;

    public VehicleAdapter(ArrayList<Vehicle> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vehicle_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VehicleAdapter.ViewHolder viewHolder, int i) {

        viewHolder.carName.setText(mFilteredList.get(i).getName());
        viewHolder.carNumber.setText(mFilteredList.get(i).getRegistrationNumber());
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = mArrayList;
                } else {

                    ArrayList<Vehicle> filteredList = new ArrayList<>();

                    for (Vehicle vehicle : mArrayList) {

                        if (vehicle.getName().toLowerCase().contains(charString) || vehicle.getRegistrationNumber().toLowerCase().contains(charString)) {

                            filteredList.add(vehicle);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Vehicle>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView carName,carNumber;
        public ViewHolder(View view) {
            super(view);

            carName = view.findViewById(R.id.vehicle_name);
            carNumber = view.findViewById(R.id.vehicle_number);
        }
    }

}