package com.example.ktandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<ProductGet> {
    Context context;


    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List<ProductGet> items) {
        super(context, resource, items);
        this.context = context;
    }
    //
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ProductGet  svitem =  getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }
        TextView ten = convertView.findViewById(R.id.tvTen);
        TextView gia = convertView.findViewById(R.id.tvGia);
        ImageView image = convertView.findViewById(R.id.image1);
        ten.setText("Tên: " + svitem.getName());
        gia.setText("Giá: " + svitem.getPrice() + "");
        Picasso.with(context).load(svitem.getPicture()).
                into(image);
        return convertView;
    }

}
