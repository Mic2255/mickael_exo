package com.example.myfirstapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myfirstapp.Dao.ProductDao;
import com.example.myfirstapp.ModifyProduct;
import com.example.myfirstapp.ProductDeleteSuppr;
import com.example.myfirstapp.ProductDetailActiviry;
import com.example.myfirstapp.ProductList;
import com.example.myfirstapp.R;
import com.example.myfirstapp.entities.Product;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private TextView prod_name;
    private TextView prod_price;
    private TextView prod_qt;
    private Context context;
    private List<Product> products;
    private LayoutInflater inflater;
    private ProductDao productDao ;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.inflate(R.layout.list_product_adapter, null);

        productDao = new ProductDao(context);

        Product currentProduct = getItem(i);
        String productName = currentProduct.name;
        double productPrice = currentProduct.price;
        double productstock = currentProduct.quantityInStock;
        double productalert = currentProduct.alertQuantity;
        String iproductstock = Double.toString(productstock);

        prod_name = view.findViewById(R.id.nom_produit);
        prod_price = view.findViewById(R.id.prix_produit);


        prod_name.setText(productName);
        prod_price.setText(productPrice + " FCFA");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ProductDetailActiviry.class);
                intent.putExtra("MY_PROD", getItem(i));
                context.startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_modify).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ModifyProduct.class);
                intent.putExtra("PROD_R", getItem(i));
                context.startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ProductDeleteSuppr.class);
                intent.putExtra("PROD_D", getItem(i));
                context.startActivity(intent);

            }
        });


        return view;
    }
}
