package com.ashishpanjwani.tripapp.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.ashishpanjwani.tripapp.Model.Drivers;
import com.ashishpanjwani.tripapp.R;

import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.ViewHolder> implements Filterable {
    private ArrayList<Drivers> mArrayList;
    private ArrayList<Drivers> mFilteredList;

    public DriverAdapter(ArrayList<Drivers> arrayList) {
        mArrayList = arrayList;
        mFilteredList = arrayList;
    }

    @Override
    public DriverAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.driver_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DriverAdapter.ViewHolder viewHolder, int i) {

        viewHolder.carName.setText(mFilteredList.get(i).getName());
        viewHolder.mobileNumber.setText(mFilteredList.get(i).getMobileNo());
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

                    ArrayList<Drivers> filteredList = new ArrayList<>();

                    for (Drivers drivers : mArrayList) {

                        if (drivers.getName().toLowerCase().contains(charString) || drivers.getMobileNo().toLowerCase().contains(charString)) {

                            filteredList.add(drivers);
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
                mFilteredList = (ArrayList<Drivers>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView carName,mobileNumber;
        public ViewHolder(View view) {
            super(view);

            carName = view.findViewById(R.id.driver_name);
            mobileNumber = view.findViewById(R.id.mobile_number);
        }
    }

}