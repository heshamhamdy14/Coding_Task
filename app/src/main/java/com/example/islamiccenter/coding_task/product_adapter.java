package com.example.islamiccenter.coding_task;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.islamiccenter.coding_task.data.Image;
import com.example.islamiccenter.coding_task.data.ProductData;
import com.squareup.picasso.Picasso;



public class product_adapter extends ArrayAdapter <ProductData> {

    //ProductData productData;
   // Image image;

    public product_adapter(@NonNull Context context , @NonNull ProductData[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_layout, parent, false);

        }


        final ProductData productData = getItem(position);
        final Image image= new Image();
        TextView price = (TextView) convertView.findViewById(R.id.product_price);
        price.setText(productData.getPrice());

        ImageView product_image = (ImageView) convertView.findViewById(R.id.product_image);
        Picasso.with(getContext()).load(image.getUrl()).into(product_image);

        TextView desc = (TextView) convertView.findViewById(R.id.product_desc);
        desc.setText(productData.getProductDescription());
        return convertView;
    }
}