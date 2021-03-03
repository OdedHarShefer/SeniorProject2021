package com.example.seniorproject2021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    private Context ctx;
    private int custResourceId;
    private List<Customer> data;

    public CustomerAdapter(@NonNull Context context, int resource, @NonNull List<Customer> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.custResourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.custResourceId, null);

        Customer cust = this.data.get(position);
        TextView name = v.findViewById(R.id.textViewName);
        name.setText(cust.getName());
        ImageView image = v.findViewById(R.id.imageView);
        image.setImageBitmap(cust.getImage());
        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public Customer getCustomer(int position) {
        return this.data.get(position);
    }
}
