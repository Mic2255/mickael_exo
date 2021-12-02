package com.example.myfirstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.myfirstapp.Dao.ProductDao;
import com.example.myfirstapp.adapter.ProductAdapter;
import com.example.myfirstapp.entities.Product;
import com.example.myfirstapp.vebServices.ProductWebService;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends AppCompatActivity {

    private ListView listView ;
    private ProductDao productDao;
    List<Product> productList = new ArrayList<>() ;
    private ProductAdapter productAdapter ;
    protected  static int MAIN_CALL = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        listView = findViewById(R.id.list_prod);
        ProductWebService productWebService = new ProductWebService();

        productAdapter = new ProductAdapter(this, productList);
        productDao = new ProductDao(this);


        new Thread(new Runnable() {
            final List<Product> productListlo = new ArrayList<>() ;
            @Override
            public void run() {
                productListlo.addAll(productWebService.getProducts()) ;
                runOnUiThread(()->{
                    productList.addAll(productListlo);
                    productAdapter.notifyDataSetChanged();
                });
            }
        }).start();

        /*
        new Thread(new Runnable() {
           final List<Product> productListlo = new ArrayList<>() ;
            @Override
            public void run() {
                productListlo.addAll(productDao.findAll()) ;
                runOnUiThread(()->{
                    productList.addAll(productListlo);
                    productAdapter.notifyDataSetChanged();
                });
            }
        }).start();

         */

        listView.setAdapter(new ProductAdapter(this, productList));
/*
        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductList.this, MainActivity.class);
                startActivity(intent);
            }
        });

 */

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        getMenuInflater().inflate(R.menu.menu_prin, menu);
        return super.onCreateOptionsMenu(menu);

    }


    public void creer(MenuItem menuItem){
        Intent intent = new Intent(ProductList.this, MainActivity.class);
        startActivityIfNeeded(intent, MAIN_CALL);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MAIN_CALL && resultCode == RESULT_OK){
            Product product = (Product) data.getSerializableExtra("MY_P");
            productList.add(product);
            productAdapter.notifyDataSetChanged();
        }
    }
}