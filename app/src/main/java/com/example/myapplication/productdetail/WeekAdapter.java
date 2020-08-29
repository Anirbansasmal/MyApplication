package com.example.myapplication.productdetail;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.MyViewHolder> {
    private ArrayList<String> Weeks;
    /* access modifiers changed from: private */
    public ArrayList<String> WeeksSelectedArray = new ArrayList<>();
Context context;
    public WeekAdapter(ArrayList<String> WEEKS) {
        this.Weeks = WEEKS;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.week_layout_row, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if (Weeks.get(position).equals("Monday")){
            holder.weekTV.setText("Mo");
        }
        if (Weeks.get(position).equals("Tuesday")){
            holder.weekTV.setText("Tu");
        }
        if (Weeks.get(position).equals("Wednesday")){
            holder.weekTV.setText("We");
        }
        if (Weeks.get(position).equals("Thursday")){
            holder.weekTV.setText("Th");
        }
        if (Weeks.get(position).equals("Friday")){
            holder.weekTV.setText("Fr");
        }
        if (Weeks.get(position).equals("Saturday")){
            holder.weekTV.setText("Sa");
        }
        if (Weeks.get(position).equals("Sunday")){
            holder.weekTV.setText("Su");
        }
        holder.weekTV.setSelected(true);
        holder.weekTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (holder.weekTV.isSelected()) {
                    holder.weekTV.setTextColor(Color.WHITE); //16711936
                    holder.weekTV.setBackgroundResource(R.drawable.custom_week);
//                    context.getResources()
                    WeekAdapter.this.WeeksSelectedArray.add(Weeks.get(position));
                    holder.weekTV.setSelected(false);
                } else {
                    holder.weekTV.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    holder.weekTV.setTextColor(Color.BLACK);
                    holder.weekTV.setBackgroundResource(R.drawable.custom_search);
                    WeekAdapter.this.WeeksSelectedArray.remove(holder.weekTV.getText().toString());
                    holder.weekTV.setSelected(true);
                }
                Log.d("tag", "onClick: " + WeekAdapter.this.WeeksSelectedArray.toString());
            }
        });
    }

    public ArrayList<String> getWeeksSelectedArray() {
        if (this.WeeksSelectedArray.equals(null)){
            this.WeeksSelectedArray.add("novalue");
            return this.WeeksSelectedArray;
        }else {
            return this.WeeksSelectedArray;
        }

    }

    public int getItemCount() {
        return this.Weeks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView weekTV;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.weekTV = (TextView) itemView.findViewById(R.id.weekTV);
        }
    }
}
