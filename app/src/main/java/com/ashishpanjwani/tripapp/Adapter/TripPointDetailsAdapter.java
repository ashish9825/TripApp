package com.ashishpanjwani.tripapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashishpanjwani.tripapp.Model.TripPoint;
import com.ashishpanjwani.tripapp.R;

import java.util.List;

public class TripPointDetailsAdapter extends RecyclerView.Adapter<TripPointDetailsAdapter.ViewHolder> {

    private Context context;
    List<TripPoint> tripPointList;

    public TripPointDetailsAdapter(Context context, List<TripPoint> tripPointList) {
        this.context = context;
        this.tripPointList = tripPointList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.tripoints_details, viewGroup , false);
        return new TripPointDetailsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final TripPoint tripPoint = tripPointList.get(i);
        viewHolder.employeeName.setText(tripPoint.getEmployeeName());
        viewHolder.employeeId.setText(tripPoint.getEmployeeID());
        viewHolder.employeeMobile.setText(tripPoint.getMobileNo());
        viewHolder.startOTP.setText(tripPoint.getStartOTP());
        viewHolder.endOTP.setText(tripPoint.getEndOTP());

        viewHolder.callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNo="+91"+tripPoint.getMobileNo();
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNo));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripPointList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView employeeName;
        TextView employeeId;
        TextView employeeMobile;
        TextView startOTP;
        TextView endOTP;
        TextView address;
        ImageView callButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            employeeName = itemView.findViewById(R.id.employee_name);
            employeeId = itemView.findViewById(R.id.employee_id);
            employeeMobile = itemView.findViewById(R.id.employee_mobile);
            startOTP = itemView.findViewById(R.id.start_otp);
            endOTP = itemView.findViewById(R.id.end_otp);
            address = itemView.findViewById(R.id.employee_address);
            callButton = itemView.findViewById(R.id.call_button);
        }
    }
}
