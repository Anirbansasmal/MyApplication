package com.example.myapplication.productdetail;

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

    public WeekAdapter(ArrayList<String> WEEKS) {
        this.Weeks = WEEKS;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.week_layout_row, parent, false));
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.weekTV.setText(this.Weeks.get(position));
        holder.weekTV.setSelected(true);
        holder.weekTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (holder.weekTV.isSelected()) {
                    holder.weekTV.setTextColor(Color.GREEN); //16711936
                    WeekAdapter.this.WeeksSelectedArray.add(holder.weekTV.getText().toString());
                    holder.weekTV.setSelected(false);
                } else {
                    holder.weekTV.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    WeekAdapter.this.WeeksSelectedArray.remove(holder.weekTV.getText().toString());
                    holder.weekTV.setSelected(true);
                }
                Log.d("tag", "onClick: " + WeekAdapter.this.WeeksSelectedArray.toString());
            }
        });
    }

    public ArrayList<String> getWeeksSelectedArray() {
        return this.WeeksSelectedArray;
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
