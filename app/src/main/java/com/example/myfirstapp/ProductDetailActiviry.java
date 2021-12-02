package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfirstapp.Dao.ProductDao;
import com.example.myfirstapp.entities.Product;

public class ProductDetailActiviry extends AppCompatActivity {
    private TextView designation;
    private TextView description;
    private TextView prix;
    private TextView quantite;
    private TextView alerte;
    private MenuItem modi ;
    private MenuItem supp ;
    private ProductDao productDao;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_activiry);
        productDao = new ProductDao(this);

        designation = findViewById(R.id.myproduct_name);
        description = findViewById(R.id.myproduct_description);
        prix = findViewById(R.id.myproduct_price);
        quantite = findViewById(R.id.myproduct_quantite);
        alerte = findViewById(R.id.myproduct_alert);

        Product product = (Product) getIntent().getSerializableExtra("MY_PROD");
        designation.setText(product.name);
        description.setText(product.description);
        prix.setText(Double.toString(product.price));
        quantite.setText(Double.toString(product.quantityInStock));
        alerte.setText(Double.toString(product.alertQuantity));



    }



}