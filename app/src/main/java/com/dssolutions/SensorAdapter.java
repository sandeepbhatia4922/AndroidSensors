package com.dssolutions;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.MyVH> {
    private Context context;
    List<Sensor> list;

    public SensorAdapter(Context context, List<Sensor> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(context).inflate(R.layout.sensor_detail_row,viewGroup,false);
        return new MyVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH myVH, final int i) {

        myVH.name.setText(list.get(i).getName());
        myVH.vendor.setText("Vendor - "+list.get(i).getVendor());
        myVH.range.setText("Range - "+list.get(i).getMaximumRange());

        myVH.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = list.get(i).getName();
                int type = list.get(i).getType();

                System.out.println("name  "+name);
                Intent i = new Intent(context,SensorEventActivity.class);
                i.putExtra("sensorType",type);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {

        TextView name,vendor,range;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sensor_name);
            vendor = itemView.findViewById(R.id.sensor_vendor);
            range = itemView.findViewById(R.id.sensor_range);
        }
    }
}
