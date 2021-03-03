package com.example.seniorproject2021;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HourAdapter extends ArrayAdapter {

    private Context ctx;
    private int hourResourceId;
    private List<Hour> data;

    public HourAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.hourResourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.hourResourceId, null);
        Hour hour = this.data.get(position);
        TextView textViewHour = v.findViewById(R.id.textViewHour);
        textViewHour.setText(hour.toString());
        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void changeBackground(View v, int position) {
        Hour hour = this.data.get(position);
        if (!hour.isSelected()) {
            v.setBackgroundColor(Color.GREEN);
            hour.setSelected(true);
        }
        else {
            v.setBackgroundColor(Color.WHITE);
            hour.setSelected(false);
        }
    }
}
