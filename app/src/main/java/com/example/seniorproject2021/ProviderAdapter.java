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

public class ProviderAdapter extends ArrayAdapter<Provider> {

    private Context ctx;
    private int provResourceId;
    private List<Provider> data;

    public ProviderAdapter(@NonNull Context context, int resource, @NonNull List<Provider> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.provResourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(this.provResourceId, null);

        Provider prov = this.data.get(position);
        TextView name = v.findViewById(R.id.textViewName);
        name.setText(prov.getName());
        ImageView image = v.findViewById(R.id.imageView);
        image.setImageBitmap(prov.getImage());
        TextView profession = v.findViewById(R.id.textViewProfession);
        profession.setText(prov.getProfession());
        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public  Provider getProvider(int position) {
        return this.data.get(position);
    }
}
